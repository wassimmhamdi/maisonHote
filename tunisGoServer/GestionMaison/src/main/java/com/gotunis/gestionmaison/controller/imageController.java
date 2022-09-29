package com.gotunis.gestionmaison.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gotunis.gestionmaison.models.Image;
import com.gotunis.gestionmaison.models.Maison;
import com.gotunis.gestionmaison.repository.ImageRepository;
import com.gotunis.gestionmaison.repository.MaisonRepository;
import com.gotunis.gestionmaison.service.ImageService;
import com.gotunis.gestionmaison.service.MaisonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class imageController {

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ImageService imageService;
    @Autowired
    MaisonService maisonService;


    //upload image pour maison
    @PostMapping("/uploadmaison/{maisonId}")
    public ResponseEntity<String> uploadFileMaison(@RequestParam("file") MultipartFile[] file,
                                             @PathVariable("maisonId") long maisonId) throws IOException {
        String message = "";
        Long idOfMaison = maisonService.getById(maisonId).get().getId();
        try {

            Arrays.asList(file).stream().forEach(files -> {
                try {
                    imageService.storeMaison(files,idOfMaison);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            message = "Uploaded for home successfully: ";
//            imageService.store(file,idOfMaison);
//            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Could not upload the file ";
//            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    //upload image pour chambre
    @PostMapping("/uploadchambre")
    public ResponseEntity<String> uploadFileChambre(@RequestParam("file") MultipartFile[] file,
                                             @RequestParam("chambreId") String chambreId) throws IOException {
        String message = "";
        Long idOfChambre = new ObjectMapper().readValue(chambreId, Long.class);
        try {

            Arrays.asList(file).stream().forEach(files -> {
                try {
                    imageService.storeChambre(files,idOfChambre);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            message = "Uploaded for chambre successfully: ";
//            imageService.store(file,idOfMaison);
//            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Could not upload the file ";
//            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }
//
//    //Afficher la liste des images
//    @GetMapping("/images")
//    public ResponseEntity<List<Image>> getAllImages( ) {
//        try {
//            List<Image> images = new ArrayList<Image>();
//
//            imageRepository.findAll().forEach(images::add);
//
//             if (images.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(images, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


    //Afficher une images par id
    @GetMapping("/image/{id}")
    public ResponseEntity<String> getImageById(@PathVariable("id") String id) throws IOException {

        Optional<Image> retrievedImage = imageService.findImageByname(id);
        System.out.println(retrievedImage.get().getId());
        String encodedString = Base64.getEncoder().encodeToString(retrievedImage.get().getData());
        String img = ("data:image/jpeg;base64," + encodedString);
        return ResponseEntity.status(HttpStatus.OK).body(img);
    }


}
