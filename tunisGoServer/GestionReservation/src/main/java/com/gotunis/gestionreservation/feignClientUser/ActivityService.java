package com.gotunis.gestionreservation.feignClientUser;

import com.gotunis.gestionreservation.dto.getMicroActivity.ActiviteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "GestionActivite" )
public interface ActivityService {

    @GetMapping("/api/activity/VerifyActivity/{id}")
    public Boolean VerifyActivity(@PathVariable("id") Long id);

    @GetMapping("/api/activity/sendActivityToMS/{id}")
    public ActiviteDTO sendActivityToMS(@PathVariable("id") Long id);
}
