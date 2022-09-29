package com.gotunis.gestionactivite.feignClientUser;

import com.gotunis.gestionactivite.dto.getMicroUser.userToActivityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "gestionuser" )
public interface UserService {

    @GetMapping("/service/users/oneUser/{id}")
     userToActivityDTO getUserByIdOther(@PathVariable("id") Long id) ;

    @GetMapping("/service/users/findUser/{id}")
     Boolean findUser(@PathVariable("id") Long id);


}
