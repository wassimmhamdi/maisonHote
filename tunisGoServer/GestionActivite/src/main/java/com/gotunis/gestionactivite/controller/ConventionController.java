package com.gotunis.gestionactivite.controller;

import com.gotunis.gestionactivite.dto.ConventionDTO;
import com.gotunis.gestionactivite.dto.ConvetionFormulaireDTO;
import com.gotunis.gestionactivite.dto.getMicroMaison.MaisonDTOMSA;
import com.gotunis.gestionactivite.feignClientUser.MaisonService;
import com.gotunis.gestionactivite.mapper.ConventionMapper;
import com.gotunis.gestionactivite.model.Activite;
import com.gotunis.gestionactivite.model.Convention;
import com.gotunis.gestionactivite.repository.ActiviteRepository;
import com.gotunis.gestionactivite.service.ActiviteService;
import com.gotunis.gestionactivite.service.ConventionService;
import com.gotunis.gestionactivite.service.MaisonMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Convention")
public class ConventionController {

    @Autowired
    ConventionService conventionService;

    @Autowired
    ActiviteRepository activiteRepository;

    @Autowired
    ActiviteService activiteService;

    @Autowired
    ConventionMapper conventionMapper;

    @Autowired
    MaisonService maisonService;

    @Autowired
    MaisonMSService maisonMSService;

    //******************************Admin_API********************************************//

    //liste des convetions
    @GetMapping("/all")
    public ResponseEntity<List<ConventionDTO>> getAll(){
        try {
            List<ConventionDTO> conventionList = conventionMapper.getAllConvention();
            return new ResponseEntity<>(conventionList,new HttpHeaders(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // all approuved for admin
    @GetMapping("/allApprouved")
    public ResponseEntity<List<ConventionDTO>> getAllApprouved() {
        List<Convention> convention = conventionService.getApprove();
        List<ConventionDTO> dtoList= conventionMapper.mapToListDto(convention);
        return new ResponseEntity<>(dtoList, new HttpHeaders(), HttpStatus.OK);
    }

    //all non approuved
    @GetMapping("/allNonApprouved")
    public ResponseEntity<List<ConventionDTO>> getAllNonApprouved() {
        List<Convention> convention = conventionService.getNonApprove();
        List<ConventionDTO> dtoList= conventionMapper.mapToListDto(convention);
        return new ResponseEntity<>(dtoList, new HttpHeaders(), HttpStatus.OK);
    }

    //******************************Shared_Api********************************************//

    //detail convention
    @GetMapping("/getById/{id}")
    public ResponseEntity<ConventionDTO> getById(@PathVariable long id) {
        Optional<Convention> convention = conventionService.getById(id);
        ConventionDTO conventionList = conventionMapper.convertToConventionDTO1(convention.get());

        return new ResponseEntity<>(conventionList, new HttpHeaders(), HttpStatus.OK);
    }

    //approuver convetion en donnat id de la convetion (Prop Maison ou bien ledeaur activité)
    @PutMapping("/approuver/{id}")
    public ResponseEntity<ConventionDTO> approuver(@PathVariable("id") long id) {
        Optional<Convention> conventionData = conventionService.getById(id);
        if (conventionData.isPresent()) {
            Convention convention = conventionService.approuverConvention(id);
            ConventionDTO conventionDTO=conventionMapper.convertToConventionDTO1(convention);
            return new ResponseEntity<>(conventionDTO, new HttpHeaders(), HttpStatus.CREATED);
        }
        else {
            //throw new RuntimeException("Convention not found");
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.BAD_GATEWAY);

        }
    }

    //supprimer convetion en donnat id de la convetion (Prop Maison ou bien ledeaur activité)
    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable long id) throws Exception {
        conventionService.deleteConvention(id);
        return HttpStatus.ACCEPTED;
    }

    //******************************PropActivity_API********************************************//

    //ajouter une convention en Tant que ledeaur activité
    @PostMapping("/addPropAct")
    public ResponseEntity<Convention> addPropAct(@RequestBody ConvetionFormulaireDTO convetionFormulaireDTO){
        Activite _act= activiteService.getById(convetionFormulaireDTO.getIdactivite()).get();

        //verifier l'id du maison entré
        Boolean exBoolean= maisonService.VerifyHome(convetionFormulaireDTO.getIdMaison());

        if (exBoolean == true){
            if (_act != null ) {
                Convention convention = new Convention();
                convention.setActivite(_act);
                convention.setApprove(false);
                convention.setPropAct(true);
                convention.setPropMaison(false);

                //tester l'id du maison si il est enreg dans cet micro ou nn
                MaisonDTOMSA dtomsa=maisonMSService.getById(convetionFormulaireDTO.getIdMaison()).get();
                if (dtomsa != null){
                    convention.setMaison(dtomsa);
                }else {
                    MaisonDTOMSA maisonDTOMSA = maisonService.sendHomeToMSActivity(convetionFormulaireDTO.getIdMaison());
                    convention.setMaison(maisonDTOMSA);
                }

                Convention _convention = conventionService.add(convention);
                return new ResponseEntity<>(_convention, new HttpHeaders(), HttpStatus.OK);
            } else {
                return new ResponseEntity<Convention>(null,new HttpHeaders() ,HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity<Convention>(null,new HttpHeaders() ,HttpStatus.BAD_REQUEST);
        }
    }

    //liste des conventions(qui a demande) approuver par id User For Prop Activity
    // li howa b3ethhom w sartelhom approuved true
    @GetMapping("/getConventionPropAvtivityByIdUserApprouved/{id}")
    public ResponseEntity<List<ConventionDTO>> getConventionByIdUserApprouved(@PathVariable("id") long id) {
        List<Convention> convention = conventionService.listByIdUserAndApprove(id,true,false,true);
        List<ConventionDTO> dtoList= conventionMapper.mapToListDto(convention);
        return new ResponseEntity<>(dtoList, new HttpHeaders(), HttpStatus.OK);
    }

    //liste des conventions Non approuver (qui a demande) par id User For Prop Activity
    //li howa b3ethhom w masrthomch approuver
    @GetMapping("/getConventionPropAvtivityByIdUserNotApprouved/{id}")
    public ResponseEntity<List<ConventionDTO>> getConventionForActivity(@PathVariable("id") long id) {
        List<Convention> convention = conventionService.listByIdUserAndApprove(id,false,false,true);
        List<ConventionDTO> list= conventionMapper.mapToListDto(convention);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    //liste des conventions li tbe3tho men 3and maison lil activité non approuver
    @GetMapping("/getDemandeFromHomeToActivityNotApprouved/{id}")
    public ResponseEntity<List<ConventionDTO>> getDemandeFromHomeToActivityNotApprouved(@PathVariable("id") long id) {
        List<Convention> convention = conventionService.listByIdUserAndApprove(id,false,true,false);
        List<ConventionDTO> list= conventionMapper.mapToListDto(convention);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    //liste des conventions li tbe3tho men 3and maison lil activité w approuvehom mola activité
    @GetMapping("/getDemandeFromHomeToActivityApprouved/{id}")
    public ResponseEntity<List<ConventionDTO>> getDemandeFromHomeToActivityApprouved(@PathVariable("id") long id) {
        List<Convention> convention = conventionService.listByIdUserAndApprove(id,true,true,false);
        List<ConventionDTO> list= conventionMapper.mapToListDto(convention);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    //******************************PropHome_API********************************************//

    //ajouter une convention en Tant que Prop Maison
    @PostMapping("/addPropHome")
    public ResponseEntity<Convention> addPropHome(@RequestBody ConvetionFormulaireDTO convetionFormulaireDTO){

        Activite _act= activiteService.getById(convetionFormulaireDTO.getIdactivite()).get();
        //verifier l'id du maison entré
        Boolean exBoolean= maisonService.VerifyHome(convetionFormulaireDTO.getIdMaison());

        if (exBoolean == true){
            if (_act != null ) {
                Convention convention = new Convention();
                convention.setActivite(_act);
                convention.setApprove(false);
                convention.setPropMaison(true);
                convention.setPropAct(false);

                MaisonDTOMSA dtomsa=maisonMSService.getById(convetionFormulaireDTO.getIdMaison()).get();
                if (dtomsa != null){
                    convention.setMaison(dtomsa);
                }else {
                    MaisonDTOMSA maisonDTOMSA = maisonService.sendHomeToMSActivity(convetionFormulaireDTO.getIdMaison());
                    convention.setMaison(maisonDTOMSA);
                }

                Convention _convention = conventionService.add(convention);
                return new ResponseEntity<>(_convention, new HttpHeaders(), HttpStatus.OK);

            } else {
                return new ResponseEntity<Convention>(null,new HttpHeaders() ,HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity<Convention>(null,new HttpHeaders() ,HttpStatus.BAD_REQUEST);
        }
    }

    //liste des conventions(qui a demande) approuver par id User For Prop Maison
    // li howa b3ethhom w sartelhom approuved true
    @GetMapping("/getConventionPropHomeByIdUserApprouved/{id}")
    public ResponseEntity<List<ConventionDTO>> getConventionByUserHomeIdApprouved(@PathVariable("id") long id) {
        List<Convention> convention = conventionService.findAllByMaison_IdUserAndApproveAndPropActAndPropMaison(id,true,false,true);
        List<ConventionDTO> list= conventionMapper.mapToListDto(convention);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    //liste des conventions(qui a demande) not approuver par id User For Prop Maison
    //li howa b3ethhom w masrthomch approuver
    @GetMapping("/getConventionPropHomeByIdUserNotApprouved/{id}")
    public ResponseEntity<List<ConventionDTO>> getConventionByUserHomeIdNotApprouved(@PathVariable("id") long id) {
        List<Convention> convention = conventionService.findAllByMaison_IdUserAndApproveAndPropActAndPropMaison(id,false,false,true);
        List<ConventionDTO> list= conventionMapper.mapToListDto(convention);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    //liste des conventions li tbe3tho men 3and activite lil maison non approuver
    @GetMapping("/getDemandeFromActivityToHomeNotApprouved/{id}")
    public ResponseEntity<List<ConventionDTO>> getDemandeFromActivityToHomeNotApprouved(@PathVariable("id") long id) {
        List<Convention> convention = conventionService.listByIdUserAndApprove(id,false,false,true);
        List<ConventionDTO> list= conventionMapper.mapToListDto(convention);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    //liste des conventions li tbe3tho men 3and activité lil maison w approuvehom mola maison
    @GetMapping("/getDemandeFromActivityToHomeApprouved/{id}")
    public ResponseEntity<List<ConventionDTO>> getDemandeFromActivityToHomeApprouved(@PathVariable("id") long id) {
        List<Convention> convention = conventionService.listByIdUserAndApprove(id,true,true,false);
        List<ConventionDTO> list= conventionMapper.mapToListDto(convention);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

}
