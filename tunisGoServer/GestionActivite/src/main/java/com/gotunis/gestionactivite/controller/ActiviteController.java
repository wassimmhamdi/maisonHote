package com.gotunis.gestionactivite.controller;

import com.gotunis.gestionactivite.dto.ActiviteDTO;
import com.gotunis.gestionactivite.dto.activityGetDTO;
import com.gotunis.gestionactivite.dto.activityPostDTO;
import com.gotunis.gestionactivite.dto.getMicroUser.userToActivityDTO;
import com.gotunis.gestionactivite.feignClientUser.UserService;
import com.gotunis.gestionactivite.mapper.ActivitieMapper;
import com.gotunis.gestionactivite.model.Activite;

import com.gotunis.gestionactivite.service.ActiviteService;

import com.gotunis.gestionactivite.service.FileStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/activity")
public class ActiviteController {

    @Autowired
    ActiviteService activiteService;

    @Autowired
    FileStorageService fileStorageService;

    @Autowired
    UserService userService;


    ActivitieMapper mapper = new ActivitieMapper();

    // lazem tetbadel ya5o parametre user id authentifier
    @GetMapping("/allActivityForUser/{id}")
    public ResponseEntity<List<Activite>> getAllActivityForUser(@PathVariable("id") long id) {
        Boolean user= userService.findUser(id);
        if (user == true){
            List<Activite> activiteList = activiteService.getActivityForUserConnected(id);
            return new ResponseEntity<>(activiteList, new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.BAD_GATEWAY);
        }
    }

    //
    @GetMapping("/all")
    public ResponseEntity<List<Activite>> getAll() {
        List<Activite> activiteList = activiteService.getAll();
        return new ResponseEntity<List<Activite>>(activiteList, new HttpHeaders(), HttpStatus.OK);
    }

    //
    @GetMapping("/allByRegionAndPublished/{region}")
    public ResponseEntity<List<Activite>> getAllByRegionAndPublished(@PathVariable("region") String region) {
        boolean published = true;
        List<Activite> activity = activiteService.getByRegionAndPublished(region,published);
        return new ResponseEntity<List<Activite>>(activity, new HttpHeaders(), HttpStatus.OK);
    }

    //
    @GetMapping("/allNotPublished")
    public ResponseEntity<List<Activite>> getAllNotPublished() {
        List<Activite> activity = activiteService.getNonPublished();
        return new ResponseEntity<List<Activite>>(activity, new HttpHeaders(), HttpStatus.OK);
    }

    //
    @GetMapping("/allPublished")
    public ResponseEntity<List<Activite>> getAllPublished() {
        List<Activite> activity = activiteService.getPublished();
        return new ResponseEntity<>(activity, new HttpHeaders(), HttpStatus.OK);
    }

    //
    @PostMapping(value ="/add")
    public ResponseEntity<Activite> add(@RequestBody Activite activite)
   {
       boolean user = userService.findUser(activite.getIdClient());
       if (user == true){
           Activite savedActivity = activiteService.addActivite(activite);
           return new ResponseEntity<>(savedActivity, new HttpHeaders(), HttpStatus.CREATED);
       }else {
           return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
       }

    }

    //New ADD Activity
    @PostMapping("/addDTO")
    public ResponseEntity<Activite> addDTO(@RequestBody activityPostDTO activite)
    {
        System.out.println(activite.getIdClient());
        boolean user = userService.findUser(activite.getIdClient());
        if (user == true){
            Activite savedActivity =new Activite();
            savedActivity.setIdClient(activite.getIdClient());
            savedActivity.setName(activite.getName());
            savedActivity.setDescription(activite.getDescription());
            savedActivity.setType(activite.getType());
            savedActivity.setRegion(activite.getRegion());
            savedActivity.setPrix(activite.getPrix());
            savedActivity.setMaxParticipants(activite.getMaxParticipants());
            savedActivity.setMinParticipants(activite.getMinParticipants());
            savedActivity.setPublished(false);
            savedActivity.setAddActToken(activite.getAddActToken());
            //Activite savedActivity = activiteService.addActivite(activite);
            return new ResponseEntity<>(activiteService.addActivite(savedActivity), new HttpHeaders(), HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
        }
    }
    //
    @PutMapping("/approuver/{id}")
    public ResponseEntity<Activite> approuver(@PathVariable("id") long id) {
        Activite activity = activiteService.approuverActivite(id);

        return new ResponseEntity<Activite>(activity, new HttpHeaders(), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable long id) throws Exception {
        activiteService.deleteactivite(id);
        return HttpStatus.ACCEPTED;
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Activite> update(@PathVariable long id,@RequestBody activityGetDTO activite) throws Exception {

        Activite updatedComment = activiteService.updateComment(id,activite);
        return new ResponseEntity<Activite>(updatedComment, new HttpHeaders(), HttpStatus.ACCEPTED);
    }


    //Afficher une activity par id
    @GetMapping("/getActById/{id}")
    public ResponseEntity<Optional<Activite>> getById(@PathVariable long id) {
       Optional<Activite> activity = activiteService.getById(id);
        return new ResponseEntity<Optional<Activite>>(activity, new HttpHeaders(), HttpStatus.OK);
  }

    //get activity bty token add
    @GetMapping("/getByToken/{addActToken}")
    public ResponseEntity<Activite> getByAddActToken(@PathVariable("addActToken") String addActToken) {
        Activite activity = activiteService.findByAddActToken(addActToken);
        return new ResponseEntity<Activite>(activity, new HttpHeaders(), HttpStatus.OK);
    }

    //verify activity in other microservice
    @GetMapping("/VerifyActivity/{id}")
    public Boolean VerifyActivity(@PathVariable("id") Long id) {
        Optional<Activite> Data = activiteService.getById(id);
        if (Data.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    //sendHomeToMSActivity
    @GetMapping("/sendActivityToMS/{id}")
    public ActiviteDTO sendActivityToMS(@PathVariable("id") Long id) {
        Optional<Activite> Data = activiteService.getImageById(id);
        ActiviteDTO DTO = mapper.mapToDto(Data.get());
        if (DTO != null) {
            return DTO;
        } else {
            return null;
        }
    }

}
