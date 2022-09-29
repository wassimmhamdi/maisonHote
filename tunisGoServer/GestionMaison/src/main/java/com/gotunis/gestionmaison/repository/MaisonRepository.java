package com.gotunis.gestionmaison.repository;


import com.gotunis.gestionmaison.models.Maison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface MaisonRepository extends JpaRepository<Maison, Long> {


   Optional<Maison> findById(long id);


   List<Maison> findByPrixResMaisonAndApprouver(int prixRes_maison,Boolean approuver);

    List<Maison> findByApprouver(boolean approuver);

    List<Maison> findByIdUser(long id);

    List<Maison> findByIdUserAndApprouver(long id,boolean approuver);

    List<Maison> findByRegionMaisonAndApprouver(String region,boolean approuver);

    List<Maison> findByPrixResMaisonAndRegionMaisonAndApprouver(int prixRes_maison,String region,boolean approuver);

    List<Maison> findByNbrChambreAndApprouver(int nbChambre,boolean approuver);

    List<Maison> findByPrixResMaisonAndRegionMaisonAndNbrChambreAndApprouver(int prixRes_maison,String region,int nbChambre,boolean approuver);

    List<Maison> findByIsChambresResAndApprouver(boolean isChambre,boolean approuver);

    List<Maison> findByIsHouseResAndApprouver(boolean isHouse,boolean approuver);

    List<Maison> findAll();
    
    Maison findByAddHouseToken(String addHouseToken);

    List<Maison> deleteById(long id );
}

