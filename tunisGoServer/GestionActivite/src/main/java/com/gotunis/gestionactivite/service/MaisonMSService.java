package com.gotunis.gestionactivite.service;

import com.gotunis.gestionactivite.dto.getMicroMaison.MaisonDTOMSA;
import com.gotunis.gestionactivite.model.Activite;
import com.gotunis.gestionactivite.repository.MaisonMSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MaisonMSService {

    @Autowired
    MaisonMSRepository maisonMSRepository;

    public Optional<MaisonDTOMSA> getById(long id) {
        return maisonMSRepository.findById(id);
    }


}
