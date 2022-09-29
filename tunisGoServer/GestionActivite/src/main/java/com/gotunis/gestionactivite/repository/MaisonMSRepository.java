package com.gotunis.gestionactivite.repository;

import com.gotunis.gestionactivite.dto.getMicroMaison.MaisonDTOMSA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaisonMSRepository extends JpaRepository<MaisonDTOMSA, Long> {
    Optional<MaisonDTOMSA> findById(long id);
}
