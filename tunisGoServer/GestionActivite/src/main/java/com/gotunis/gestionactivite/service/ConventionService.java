package com.gotunis.gestionactivite.service;

import com.gotunis.gestionactivite.model.Activite;
import com.gotunis.gestionactivite.model.Convention;
import com.gotunis.gestionactivite.repository.ConventionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConventionService {
    @Autowired
    ConventionRepository conventionRepository;

    //******************************Admin_API********************************************//
    //all convension
    public List<Convention> getAll() {
        return conventionRepository.findAll();
    }
    //get Convention not approve
    public List<Convention> getNonApprove(){
        return conventionRepository.findByApprove(false);
    }
    //get Convention approve
    public List<Convention> getApprove(){
        return conventionRepository.findByApprove(true);
    }

    //******************************PropActivity_API********************************************//

    //List Convention for specific client activity id and approuver and prop maison
    public List<Convention> findAllByActivite_IdClientAndApproveAndPropMaison(Long id, boolean approuver,boolean PropMaison){
        return conventionRepository.findAllByActivite_IdClientAndApproveAndPropMaison(id,approuver,PropMaison);
    }

    //List Convention for specific client activity(qui a cree) id and approuver qui a cree
    public List<Convention> listByIdUserAndApprove(Long id, boolean approuver,boolean PropMaison,boolean propAct){
        return conventionRepository.findAllByActivite_IdClientAndApproveAndPropMaisonAndPropAct(id,approuver,PropMaison,propAct);
    }

    //******************************PropHome_API********************************************//

    //List Convention for specific client activity id and approuver and prop maison
    public List<Convention> findAllByMaison_IdUserAndApproveAndPropAct(Long id, boolean approuver,boolean propAct){
        return conventionRepository.findAllByMaison_IdUserAndApproveAndPropAct(id,approuver,propAct);
    }

    //List Convention for specific Home
    public List<Convention> findAllByMaison_IdUserAndApproveAndPropActAndPropMaison(Long id, boolean approuver,boolean propAct,boolean propMaison){
        return conventionRepository.findAllByMaison_IdUserAndApproveAndPropActAndPropMaison(id,approuver,propAct,propMaison);
    }

    //******************************Shared_API********************************************//
    //add one
    public Convention add(Convention convention) {
        return conventionRepository.save(convention);
    }

    //get by id
    public Optional<Convention> getById(long id) {
        return conventionRepository.findById(id);
    }

    //approuver une convention
    public Convention approuverConvention(long id) {
        Optional<Convention> conventionData = conventionRepository.findById(id);

        if (conventionData.isPresent()) {
            Convention _convention= conventionData.get();
            _convention.setApprove(true);
            return conventionRepository.save(_convention);
        }  else {
            throw new RuntimeException("Convention not found");
        }
    }

    //delete convention
    public void deleteConvention(long id) throws Exception {
        Convention convention = conventionRepository.getOne(id);
        if (convention != null) {
            conventionRepository.deleteById(id);
        } else {
            throw new RuntimeException("Convention not found");
        }
    }

}
