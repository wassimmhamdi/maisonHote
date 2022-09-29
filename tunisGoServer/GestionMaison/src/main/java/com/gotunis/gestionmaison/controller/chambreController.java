package com.gotunis.gestionmaison.controller;

import com.gotunis.gestionmaison.mapper.ChambreMapper;
import com.gotunis.gestionmaison.mapper.MaisonMapper;
import com.gotunis.gestionmaison.models.Chambre;
import com.gotunis.gestionmaison.models.Maison;
import com.gotunis.gestionmaison.models.dto.ChambreDTO;
import com.gotunis.gestionmaison.models.dto.ChambreSendDTO;
import com.gotunis.gestionmaison.models.dto.MaisonDTO;
import com.gotunis.gestionmaison.repository.ChambreRepository;
import com.gotunis.gestionmaison.repository.MaisonRepository;
import com.gotunis.gestionmaison.service.ChambreService;
import com.gotunis.gestionmaison.service.MaisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class chambreController {


    @Autowired
    ChambreRepository chambreRepository;

    @Autowired
    ChambreService chambreService;

    @Autowired
    private MaisonRepository maisonRepository;

    ChambreMapper mapper = new ChambreMapper();

    //ajouter une chambre
    @PostMapping("/addChambre/{id_maison}")
    public ResponseEntity<ChambreDTO> add(@RequestBody Chambre chambre, @PathVariable(name="id_maison") long id_maison) {
        Maison maison= maisonRepository.findById(id_maison).get();
        if (maison!= null){
            chambre.setMaisonId(maison);
            Chambre savedChambre = chambreRepository.save(chambre);
            return new ResponseEntity<ChambreDTO>(mapper.mapChambreToDto(savedChambre), new HttpHeaders(), HttpStatus.CREATED);
        }
        else {

            return new ResponseEntity<ChambreDTO>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }


    //Afficher la liste des chambres
    @GetMapping("/chambres")
    public ResponseEntity<?> getAllChambres(@RequestParam(required = false) String type) {
        try
        {
            List<Chambre> Chambres = chambreRepository.findAll();

            if (Chambres.isEmpty())
                {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
            else {
                List<ChambreDTO> res =Chambres.stream().map(chambre -> {
                    return mapper.mapChambreToDto(chambre);
                }).collect(Collectors.toList());
                return new ResponseEntity<>(res, HttpStatus.OK);
            }

        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //Afficher une chambre par id
    @GetMapping("/chambre/{id}")
    public ChambreDTO getMaisonById(@PathVariable("id") Long id) {
        Optional<Chambre> chambreData = chambreService.getImageById(id);
        ChambreDTO chambreDTO = mapper.mapChambreToDto(chambreData.get());

        if (chambreDTO != null) {
            System.out.println("in controller "+chambreDTO.getType());
            return chambreDTO;

        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return null;
        }
    }


    //modifier une chambre par id
    @PutMapping("/updateChambre/{id}")
    public ResponseEntity<ChambreDTO> updateChambre(@PathVariable("id") long id, @RequestBody Chambre chambre) {
        Optional<Chambre> chambreData = chambreRepository.findById(id);

        if (chambreData.isPresent()) {
            Chambre _chambre = chambreData.get();
            _chambre.setType(chambre.getType());
            _chambre.setPrixLogementSimple(chambre.getPrixLogementSimple());
            _chambre.setPrixLogementPD(chambre.getPrixLogementPD());
            _chambre.setPrixLogementDP(chambre.getPrixLogementDP());
            _chambre.setPrixLogementPC(chambre.getPrixLogementPC());
            _chambre.setNbLits(chambre.getNbLits());
            _chambre.setNbPersonne(chambre.getNbPersonne());

            Chambre saved =chambreRepository.save(_chambre);
            ChambreDTO chambreDTO = mapper.mapChambreToDto(saved);

            return new ResponseEntity<>(chambreDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //effacer une chambre par id
    @DeleteMapping("/deletechambre/{id}")
    public ResponseEntity<HttpStatus> deleteChambre(@PathVariable("id") long id) {
        try {
            chambreRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //Afficher une chambre par id Send To DTO
    @GetMapping("/getMaisonDTOToReservationById/{id}")
    public ChambreSendDTO getMaisonDTOToReservationById(@PathVariable("id") Long id) {
        Optional<Chambre> chambreData = chambreService.getImageById(id);
        ChambreSendDTO chambreDTO = mapper.mapChambreToDtoRes(chambreData.get());
        if (chambreDTO != null) {
            System.out.println("in controller "+chambreDTO.getType());
            return chambreDTO;
        } else {
            return null;
        }
    }

    //Afficher une chambre par id
    @GetMapping("/verifyChambre/{id}")
    public boolean verifyChambre(@PathVariable("id") Long id) {
        Optional<Chambre> chambreData = chambreService.getImageById(id);
        ChambreDTO chambreDTO = mapper.mapChambreToDto(chambreData.get());
        if (chambreDTO != null) {
            return true;
        } else {
            return false;
        }
    }
}
