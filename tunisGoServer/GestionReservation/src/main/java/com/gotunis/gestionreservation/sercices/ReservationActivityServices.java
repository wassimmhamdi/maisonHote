package com.gotunis.gestionreservation.sercices;

import com.gotunis.gestionreservation.dto.ReservActivityPostDTO;
import com.gotunis.gestionreservation.dto.getMicroActivity.ActiviteDTO;
import com.gotunis.gestionreservation.feignClientUser.ActivityService;
import com.gotunis.gestionreservation.models.ReservationActivity;
import com.gotunis.gestionreservation.models.ReservationManagement;
import com.gotunis.gestionreservation.repositories.ReservationActRepository;
import com.gotunis.gestionreservation.repositories.ReservationActivityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class ReservationActivityServices {
    @Autowired
    ReservationActivityUser reservationActRepository;

    @Autowired
    ReservationActRepository reservationRepository;

    @Autowired
    ActivityService activityService;

    //verifier la date
    public boolean verifierReservation(long idM, long idAct, Date date){
        //ReservationManagement reservationManagement= reservationRepository.findById(reservActivityPostDTO.getIdReservation()).get();
        return reservationActRepository.findByIdMaisonAndIdActivityAndDateAndDoneAndCancelAndValidate(idM,idAct,date,false,false,false).isPresent();
    }

    //Reservation des activity dans un maison d'hote par chambre
    public ReservationActivity reservationActivityWithHome(ReservActivityPostDTO reservActivityPostDTO){
        //get reservation par id Reservation
        ReservationManagement reservationManagement= reservationRepository.findById(reservActivityPostDTO.getIdReservation()).get();
        //get id Maison from Reservation Entity
        long idMais= reservationManagement.getIdMaison();

        //verifier s'il existe déjà une reservation par la meme date, Maison pour la meme activité
        boolean verifier=verifierReservation(idMais,reservActivityPostDTO.getIdActivity(),reservActivityPostDTO.getDate());

        if (verifier == true){
            //get entity with the same value
            ReservationActivity reservationActivityExist= reservationActRepository.findByIdMaisonAndIdActivityAndDateAndDoneAndCancelAndValidate(idMais, reservActivityPostDTO.getIdActivity(),reservActivityPostDTO.getDate(),false,false,false).get();
            //ajouter a la liste la nouvelle reservation
            reservationActivityExist.getReservationManagementList().add(reservationManagement);
            //ajouter 1 à count
            int nb=(reservationActivityExist.getCount())+1;
            reservationActivityExist.setCount(nb);
                //appeller microservice activity pour verifier le nb min ou max
                ActiviteDTO activity=activityService.sendActivityToMS(reservActivityPostDTO.getIdActivity());
                if (nb >= activity.getMin_participants() && nb <= activity.getMax_participants()){
                    //lancer notification
                }


        }else {

        }

        return null;
    }

    public ReservationActivity reservationActivityWithHomeEmna(ReservActivityPostDTO reservActivityPostDTO){
        //get reservation par id Reservation
        ReservationManagement reservationManagement= reservationRepository.findById(reservActivityPostDTO.getIdReservation()).get();
        //get id Maison from Reservation Entity
        long idMais= reservationManagement.getIdMaison();

        //verifier s'il existe déjà une reservation par la meme date, Maison pour la meme activité
        boolean verifier=verifierReservation(idMais,reservActivityPostDTO.getIdActivity(),reservActivityPostDTO.getDate());

        if (verifier == true){
            //get entity with the same value
            ReservationActivity reservationActivityExist= reservationActRepository.findByIdMaisonAndIdActivityAndDateAndDoneAndCancelAndValidate(idMais, reservActivityPostDTO.getIdActivity(),reservActivityPostDTO.getDate(),false,false,false).get();
            //ajouter a la liste la nouvelle reservation
            reservationActivityExist.getReservationManagementList().add(reservationManagement);
            //ajouter 1 à count
            int nb=(reservationActivityExist.getCount())+1;
            reservationActivityExist.setCount(nb);
            //appeller microservice activity pour verifier le nb min ou max
            ActiviteDTO activity=activityService.sendActivityToMS(reservActivityPostDTO.getIdActivity());
            if (nb >= activity.getMin_participants() && nb <= activity.getMax_participants()){
                //lancer notification
            }


        }else {

        }

        return null;
    }

}
