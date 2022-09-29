package com.gotunis.gestionmaison.service;

import com.gotunis.gestionmaison.models.Maison;
import com.gotunis.gestionmaison.repository.MaisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MaisonService {

    @Autowired
    private MaisonRepository maisonRepository;


    // all activity published+not
    public List<Maison> getAll() {
        return maisonRepository.findAll();
    }

    // add Home
    public Maison addMaison(Maison maison) {
        return maisonRepository.save(maison);
    }

    //approuver home
    public Maison approuverMaison(long id) {
        Optional<Maison> Data = maisonRepository.findById(id);

        if (Data.isPresent()) {
            Maison _maison= Data.get();
            _maison.setApprouver(true);
            return maisonRepository.save(_maison);
        }  else {
            throw new RuntimeException("Maison not found");
        }
    }

    //deleteHome
    public void deleteHome(long id) throws Exception {
        Maison activite = maisonRepository.getOne(id);
        if (activite != null) {
            maisonRepository.deleteById(id);
        } else {
            throw new RuntimeException("Maison not found");
        }
    }

    //updateHome
    public Maison updateHome(long id, Maison homeRequest) throws Exception{
        Maison edited = maisonRepository.getOne(id);
        if (edited != null) {
            if (homeRequest.getAdresseMaison() != null) {
                edited.setAdresseMaison(homeRequest.getAdresseMaison());
            }
            if (homeRequest.getNomMaison() != null) {
                edited.setNomMaison(homeRequest.getNomMaison());
            }
            if (homeRequest.getRegionMaison() != null) {
                edited.setRegionMaison(homeRequest.getRegionMaison());
            }
            if (homeRequest.getAdresseMaison() != null) {
                edited.setAdresseMaison(homeRequest.getAdresseMaison());
            }
            if (homeRequest.getDescreptionMaison() != null) {
                edited.setDescreptionMaison(homeRequest.getDescreptionMaison());
            }
            if (homeRequest.getPrixResMaison() != 0) {
                edited.setPrixResMaison(homeRequest.getPrixResMaison());
            }
            if (homeRequest.getNbrChambre() != 0) {
                edited.setNbrChambre(homeRequest.getNbrChambre());
            }
            if (homeRequest.getSalleDeBain() != 0) {
                edited.setSalleDeBain(homeRequest.getSalleDeBain());
            }
            if (homeRequest.getCapacitéTotale() != 0) {
                edited.setCapacitéTotale(homeRequest.getCapacitéTotale());
            }
            return maisonRepository.save(edited);
        }else {
            throw new RuntimeException("Maison not found");
        }
    }

    //get  not published Admin
    public List<Maison> getNonPublished(){
        return maisonRepository.findByApprouver(false);
    }

    //get  published Admin
    public List<Maison> getPublished(){
        return maisonRepository.findByApprouver(true);
    }

    //get  not published Specific User
    public List<Maison> getNonPublishedUser(long id){
        return maisonRepository.findByIdUserAndApprouver(id,false);
    }

    //get   published Specific User
    public List<Maison> getPublishedUser(long id){
        return maisonRepository.findByIdUserAndApprouver(id,true);
    }

    //find by region
    public List<Maison> getByRegion(String region){
        return maisonRepository.findByRegionMaisonAndApprouver(region,true);
    }

    //find by prix
    public List<Maison> getByPrixMaison(int prix){
        return maisonRepository.findByPrixResMaisonAndApprouver(prix,true);
    }

    //find by prix and region
    public List<Maison> getByPrixMaisonAndRegion(int prix,String region){
        return maisonRepository.findByPrixResMaisonAndRegionMaisonAndApprouver(prix,region,true);
    }

    //find by nb chambre
    public List<Maison> getByPrixMaisonAndRegion(int nbChambre){
        return maisonRepository.findByNbrChambreAndApprouver(nbChambre,true);
    }

    //find by PrixResMaison And RegionMaison And NbrChambre
    public List<Maison> findByPrixResMaisonAndRegionMaisonAndNbrChambre(int prixRes_maison,String region,int nbChambre){
        return maisonRepository.findByPrixResMaisonAndRegionMaisonAndNbrChambreAndApprouver(prixRes_maison,region,nbChambre,true);
    }

   // find By IsChambresRes And Approuver
    public List<Maison> findByIsChambresResAndApprouver(){
        return maisonRepository.findByIsChambresResAndApprouver(true,true);
    }

    //find By IsHouseRes And Approuver
    public List<Maison> findByIsHouseResAndApprouver(){
        return maisonRepository.findByIsHouseResAndApprouver(true,true);
    }


    public Optional<Maison> getImageById(Long id) {
        System.out.println(id);
        Optional<Maison> res = null;
        if (id != null) {
            res = maisonRepository.findById(id);
            System.out.println("in maison service"+res.get().getNomMaison());
        }
        return res;
    }


    //get all for the user connected
    public List<Maison> getForUserConnected(long id) {
        return maisonRepository.findByIdUser(id);
    }

    public Optional<Maison> getById(long id) {
        return maisonRepository.findById(id);
    }

    public Maison findByAddHouseToken(String id) {
        return maisonRepository.findByAddHouseToken(id);
    }

}
