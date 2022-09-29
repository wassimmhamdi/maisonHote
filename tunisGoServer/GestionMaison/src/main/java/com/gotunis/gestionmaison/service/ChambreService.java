package com.gotunis.gestionmaison.service;

import com.gotunis.gestionmaison.models.Chambre;
import com.gotunis.gestionmaison.models.Maison;
import com.gotunis.gestionmaison.repository.ChambreRepository;
import com.gotunis.gestionmaison.repository.MaisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ChambreService {


    @Autowired
    private ChambreRepository chambreRepository;


    public Optional<Chambre> getImageById(Long id) {
        System.out.println(id);
        Optional<Chambre> res = null;
        if (id != null) {
            res = chambreRepository.findById(id);
            System.out.println("in chambre srvice"+res.get().getType());
        }
        return res;
    }
}
