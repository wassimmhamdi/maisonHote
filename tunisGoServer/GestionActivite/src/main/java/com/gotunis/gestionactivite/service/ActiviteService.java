package com.gotunis.gestionactivite.service;

import com.gotunis.gestionactivite.dto.activityGetDTO;
import com.gotunis.gestionactivite.dto.getMicroUser.userToActivityDTO;
import com.gotunis.gestionactivite.feignClientUser.UserService;
import com.gotunis.gestionactivite.model.Activite;
import com.gotunis.gestionactivite.repository.ActiviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActiviteService {

    @Autowired
    ActiviteRepository activiteRepository;

    public List<Activite> getAll() {
        return activiteRepository.findAll();
    }

    //get activity not published
    public List<Activite> getNonPublished(){
        return activiteRepository.findByPublished(false);
    }

    //get activity published
    public List<Activite> getPublished(){
        return activiteRepository.findByPublished(true);
    }

    //get activity published for specific region
    public List<Activite> getByRegionAndPublished(String region, boolean test){
        return activiteRepository.findByRegionAndPublished(region, test);
    }

    //get all activity for the user connected
    public List<Activite> getActivityForUserConnected(long id){
        return activiteRepository.findByIdClient(id);
    }

    public Activite addActivite(Activite activite) {
        return activiteRepository.save(activite);
    }

    public Activite approuverActivite(long id) {
        Optional<Activite> activityData = activiteRepository.findById(id);

        if (activityData.isPresent()) {
            Activite _activite= activityData.get();
            _activite.setPublished(true);
            return activiteRepository.save(_activite);
        }  else {
            throw new RuntimeException("Activite not found");
        }
    }

    public void deleteactivite(long id) throws Exception {
        Activite activite = activiteRepository.getOne(id);
        if (activite != null) {
            activiteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Activite not found");
        }
    }

    public Activite updateComment(long id, activityGetDTO activityRequest) throws Exception{

        Activite editedActivite = activiteRepository.getOne(id);

        editedActivite.setName(activityRequest.getName());
        editedActivite.setDescription(activityRequest.getDescription());
        editedActivite.setType(activityRequest.getType());
        editedActivite.setRegion(activityRequest.getRegion());
        editedActivite.setPrix(activityRequest.getPrix());
        editedActivite.setMinParticipants(activityRequest.getMinParticipants());
        editedActivite.setMinParticipants(activityRequest.getMinParticipants());

        return activiteRepository.save(editedActivite);
//        } else {
//            throw new RuntimeException("Activite not found");
//        }
    }

    //
    public Optional<Activite> getImageById(Long id) {
        System.out.println(id);
        Optional<Activite> res = null;
        if (id != null) {
            res = activiteRepository.findById(id);
            System.out.println("in activitie srvice"+res.get().getName());
        }
        return res;
    }

    public Optional<Activite> getById(long id) {
        return activiteRepository.findById(id);
    }

    public Activite findByAddActToken(String token) {
        return activiteRepository.findByAddActToken(token);
    }
}
