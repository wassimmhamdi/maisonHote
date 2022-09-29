package com.gotunis.gestionmaison.controller;
import com.gotunis.gestionmaison.feignClientUser.UserService;
import com.gotunis.gestionmaison.mapper.MaisonMapper;
import com.gotunis.gestionmaison.models.Maison;
import com.gotunis.gestionmaison.models.dto.MaisonDTO;
import com.gotunis.gestionmaison.models.dto.sendMicroActivity.MaisonDTOMSA;
import com.gotunis.gestionmaison.service.MaisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")

public class maisonController {

    @Autowired
    MaisonService maisonService;

    @Autowired
    UserService userService;

    MaisonMapper mapper = new MaisonMapper();

    // all home published and not
    @GetMapping("/all")
    public ResponseEntity<List<MaisonDTO>> getAll() {
        List<Maison> list = maisonService.getAll();
        List<MaisonDTO> listMaisonDTO = mapper.mapToListDto(list);
        return new ResponseEntity<>(listMaisonDTO, new HttpHeaders(), HttpStatus.OK);
    }

    //ajouter maison
    @PostMapping(value ="/add")
    public ResponseEntity<Maison> addM(@RequestBody Maison maison)
    {
        Boolean user= userService.findUser(maison.getIdUser());
        if (user == true) {
            Maison saved = maisonService.addMaison(maison);
            return new ResponseEntity<>(saved, new HttpHeaders(), HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.BAD_GATEWAY);
        }
    }

    //Approuver Home for admin
    @PutMapping("/approuver/{id}")
    public ResponseEntity<MaisonDTO> approuver(@PathVariable("id") long id) throws Exception {
        Maison m1=maisonService.getImageById(id).get();
        if (m1 != null){
            Maison maison = maisonService.approuverMaison(id);
            System.out.println("approuved");
            return new ResponseEntity<>(mapper.mapToDto(maison), new HttpHeaders(), HttpStatus.CREATED);
        }else {
            System.out.println("not found");
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    //edit home
    @PutMapping("/edit/{id}")
    public ResponseEntity<Maison> update(@PathVariable long id,@RequestBody Maison maison) throws Exception {
            Maison updated = maisonService.updateHome(id, maison);
            return new ResponseEntity<Maison>(updated, new HttpHeaders(), HttpStatus.ACCEPTED);
    }

    //delete home
    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable long id) throws Exception {
        maisonService.deleteHome(id);
        return HttpStatus.ACCEPTED;
    }

    //Afficher une maison par id
    @GetMapping("/maison/{id}")
    public MaisonDTO getMaisonById(@PathVariable("id") Long id) {
        Optional<Maison> maisonData = maisonService.getImageById(id);
        MaisonDTO maisonDTO = mapper.mapToDto(maisonData.get());

        if (maisonDTO != null) {
            System.out.println("in controller "+maisonDTO.getNomMaison());
            return maisonDTO;
        } else {
            return null;
        }
    }

    // lazem tetbadel ya5o parametre user id authentifier
    @GetMapping("/allHomeForUser/{id}")
    public ResponseEntity<List<MaisonDTO>> getAllMaisonForUser(@PathVariable("id") long id) {
        List<Maison> maisonList = maisonService.getForUserConnected(id);
        List<MaisonDTO> listMaisonDTO = mapper.mapToListDto(maisonList);
        return new ResponseEntity<>(listMaisonDTO, new HttpHeaders(), HttpStatus.OK);
    }

    // all home Not published Admin
    @GetMapping("/getAllNotPublished")
    public ResponseEntity<List<MaisonDTO>> getAllNotPublished() {
        List<Maison> List = maisonService.getNonPublished();
        List<MaisonDTO> listMaisonDTO = mapper.mapToListDto(List);
        return new ResponseEntity<>(listMaisonDTO, new HttpHeaders(), HttpStatus.OK);
    }

    // all home published Admin
    @GetMapping("/getAllPublished")
    public ResponseEntity<List<MaisonDTO>> getAllPublished() {
        List<Maison> List = maisonService.getPublished();
        List<MaisonDTO> listMaisonDTO = mapper.mapToListDto(List);
        return new ResponseEntity<>(listMaisonDTO, new HttpHeaders(), HttpStatus.OK);
    }

    // all home published Admin
    @GetMapping("/getNonPublishedUser/{id}")
    public ResponseEntity<List<MaisonDTO>> getNonPublishedUser(@PathVariable("id") long idUser) {
        List<Maison> List = maisonService.getNonPublishedUser(idUser);
        List<MaisonDTO> listMaisonDTO = mapper.mapToListDto(List);

        return new ResponseEntity<>(listMaisonDTO, new HttpHeaders(), HttpStatus.OK);
    }

    // getPublishedUser Par user
    @GetMapping("/getPublishedUser/{id}")
    public ResponseEntity<List<MaisonDTO>> getPublishedUser(@PathVariable("id") long idUser) {
        List<Maison> maisonList = maisonService.getPublishedUser(idUser);
        List<MaisonDTO> listMaisonDTO = mapper.mapToListDto(maisonList);
        return new ResponseEntity<>(listMaisonDTO, new HttpHeaders(), HttpStatus.OK);
    }

    // getByRegion
    @GetMapping("/getByRegion/{region}")
    public ResponseEntity<List<MaisonDTO>> getByRegion(@PathVariable("region") String region) {
        List<Maison> maisonList = maisonService.getByRegion(region);
        List<MaisonDTO> listMaisonDTO = mapper.mapToListDto(maisonList);
        return new ResponseEntity<>(listMaisonDTO, new HttpHeaders(), HttpStatus.OK);
    }

    // getByPrixMaison
    @GetMapping("/getByPrixMaison/{prix}")
    public ResponseEntity<List<MaisonDTO>> getByPrixMaison(@PathVariable("prix") int prix) {
        List<Maison> maisonList = maisonService.getByPrixMaison(prix);
        List<MaisonDTO> listMaisonDTO = mapper.mapToListDto(maisonList);
        return new ResponseEntity<>(listMaisonDTO, new HttpHeaders(), HttpStatus.OK);
    }

    // getByPrixMaisonAndRegion
    @GetMapping("/getByPrixMaisonAndRegion/{prix}/{region}")
    public ResponseEntity<List<MaisonDTO>> getByPrixMaisonAndRegion(@PathVariable("prix") int prix,@PathVariable("region") String region) {
        List<Maison> maisonList = maisonService.getByPrixMaisonAndRegion(prix,region);
        List<MaisonDTO> listMaisonDTO = mapper.mapToListDto(maisonList);
        return new ResponseEntity<>(listMaisonDTO, new HttpHeaders(), HttpStatus.OK);
    }

    //verify user in other microservice
    @GetMapping("/VerifyHome/{id}")
    public Boolean VerifyHome(@PathVariable("id") Long id) {
        Optional<Maison> Data = maisonService.getImageById(id);
        if (Data.isPresent()) {
            return true;
        } else {
            return false;
        }
    }


//////// afficher maison///////////

    //Afficher la liste des maisons
//    @GetMapping("/maisons")
//    public ResponseEntity<List<MaisonDTO>> getAllMaisons() {
//        try {
//            List<Maison> maisons = new ArrayList<Maison>();
//            maisons= maisonRepository.findAll();
//            List<MaisonDTO> res = maisons.stream().map(maison -> {
//                return mapper.mapToDto(maison);
//            }).collect(Collectors.toList());
//
//            if (res.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(res, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


    //modifier une maison par id
//    @PutMapping("/updateMaison/{id}")
//    public ResponseEntity<Maison> updateMaison(@PathVariable("id") long id, @RequestBody Maison maison) {
//        Optional<Maison> maisonData = maisonRepository.findById(id);
//
//        if (maisonData.isPresent()) {
//            Maison _maison = maisonData.get();
//            _maison.setNomMaison(maison.getNomMaison());
//            _maison.setRegionMaison(maison.getRegionMaison());
//            _maison.setAdresseMaison(maison.getAdresseMaison());
//            _maison.setDescreptionMaison(maison.getDescreptionMaison());
//            _maison.setPrixResMaison(maison.getPrixResMaison());
//            _maison.setNbrChambre(maison.getNbrChambre());
//
//            return new ResponseEntity<>(maisonRepository.save(_maison), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    //effacer une maison par id
//    @DeleteMapping("/deleteMaison/{id}")
//    public ResponseEntity<HttpStatus> deleteMaison(@PathVariable("id") long id) {
//        try {
//            maisonRepository.deleteById(id);
//            System.out.println("deleted succesfully");
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


    //modifier une maison par id
//    @PutMapping("/updateMaison/{id}")
//    public ResponseEntity<Maison> updateMaison(@PathVariable("id") long id, @RequestBody Maison maison) {
//        Optional<Maison> maisonData = maisonRepository.findById(id);
//
//        if (maisonData.isPresent()) {
//            Maison _maison = maisonData.get();
//            _maison.setNomMaison(maison.getNomMaison());
//            _maison.setRegionMaison(maison.getRegionMaison());
//            _maison.setAdresseMaison(maison.getAdresseMaison());
//            _maison.setDescreptionMaison(maison.getDescreptionMaison());
//            _maison.setPrixResMaison(maison.getPrixResMaison());
//            _maison.setNbrChambre(maison.getNbrChambre());
//
//            return new ResponseEntity<>(maisonRepository.save(_maison), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    //effacer une maison par id
//    @DeleteMapping("/deleteMaison/{id}")
//    public ResponseEntity<HttpStatus> deleteMaison(@PathVariable("id") long id) {
//        try {
//            maisonRepository.deleteById(id);
//            System.out.println("deleted succesfully");
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


//get house bty token add

    @GetMapping("/getByToken/{addHouseToken}")
    public ResponseEntity<Maison> getByAddHouseToken(@PathVariable("addHouseToken") String addHouseToken) {
        Maison maison = maisonService.findByAddHouseToken(addHouseToken);
        return new ResponseEntity<Maison>(maison, new HttpHeaders(), HttpStatus.OK);
    }

    //sendHomeToMSActivity
    @GetMapping("/sendHomeToMSActivity/{id}")
    public MaisonDTOMSA sendHomeToMSActivity(@PathVariable("id") Long id) {
        Optional<Maison> maisonData = maisonService.getImageById(id);
        MaisonDTOMSA maisonDTO = mapper.mapToDtoAct(maisonData.get());
        if (maisonDTO != null) {
            return maisonDTO;
        } else {
            return null;
        }
    }


}
