package com.gotunis.gestionreservation.controllers;

import com.gotunis.gestionreservation.dto.ReservationDTO;
import com.gotunis.gestionreservation.dto.ReservationHomePostDTO;
import com.gotunis.gestionreservation.mapper.ReservationMapper;
import com.gotunis.gestionreservation.models.ReservationManagement;
import com.gotunis.gestionreservation.sercices.ReservationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/Reservation")
public class UserReservationController {

    @Autowired
    ReservationServices reservationServices;

    @Autowired
    ReservationMapper reservationMapper;

//****************************** Reservation Home **********************************************************//
   // ajouter maison
    @PostMapping(value ="/user/postReservationUSERHome")
    public ResponseEntity<ReservationDTO> addResHome(@RequestBody ReservationHomePostDTO reservationManagement)
    {
        ReservationManagement saved = reservationServices.reservationHome(reservationManagement);
        return new ResponseEntity<>(reservationMapper.mapToDto(saved), new HttpHeaders(), HttpStatus.CREATED);
    }

//****************************** Prop **********************************************************//


}
