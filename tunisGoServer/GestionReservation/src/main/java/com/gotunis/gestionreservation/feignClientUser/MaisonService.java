package com.gotunis.gestionreservation.feignClientUser;

import com.gotunis.gestionreservation.dto.getMicroMaison.ChambreSendDTO;
import com.gotunis.gestionreservation.dto.getMicroMaison.MaisonDTOMSA;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "GestionMaison" )
public interface MaisonService {
    @GetMapping("/api/VerifyHome/{id}")
    public Boolean VerifyHome(@PathVariable("id") Long id);

    @GetMapping("/api/sendHomeToMSActivity/{id}")
    public MaisonDTOMSA sendHomeToMSActivity(@PathVariable("id") Long id);

    //********************Chambre*****************************//
    @GetMapping("/api/getMaisonDTOToReservationById/{id}")
    public ChambreSendDTO getMaisonDTOToReservationById(@PathVariable("id") Long id);

    @GetMapping("/verifyChambre/{id}")
    public boolean verifyChambre(@PathVariable("id") Long id);
}
