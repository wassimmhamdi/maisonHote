package com.gotunis.gestionactivite.repository;

import com.gotunis.gestionactivite.model.Activite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActiviteRepository extends JpaRepository<Activite,Long> {

    Optional<Activite> findByName(String name);

    List<Activite> findByPublished(boolean published);

    //to return the list of activity for the user connected
    List<Activite> findByIdClient(Long idClient);


    List<Activite> findByRegionAndPublished(String region,boolean published);

    @Override
    Optional<Activite> findById(Long aLong);

    Activite findByAddActToken(String addActToken);
}
