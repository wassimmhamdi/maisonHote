package com.gotunis.gestionactivite.feignClientUser;

import com.gotunis.gestionactivite.dto.getMicroMaison.MaisonDTOMSA;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "GestionMaison" )
public interface MaisonService {
    @GetMapping("/api/VerifyHome/{id}")
    public Boolean VerifyHome(@PathVariable("id") Long id);

    @GetMapping("/api/sendHomeToMSActivity/{id}")
    public MaisonDTOMSA sendHomeToMSActivity(@PathVariable("id") Long id);
}
