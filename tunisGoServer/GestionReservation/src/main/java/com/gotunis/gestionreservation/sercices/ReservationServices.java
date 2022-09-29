package com.gotunis.gestionreservation.sercices;

import com.gotunis.gestionreservation.dto.ReservActivityPostDTO;
import com.gotunis.gestionreservation.dto.ReservationHomePostDTO;
import com.gotunis.gestionreservation.dto.getMicroMaison.MaisonDTOMSA;
import com.gotunis.gestionreservation.feignClientUser.MaisonService;
import com.gotunis.gestionreservation.feignClientUser.UserService;
import com.gotunis.gestionreservation.models.ReservationActivity;
import com.gotunis.gestionreservation.models.ReservationManagement;
import com.gotunis.gestionreservation.repositories.ReservationActRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ReservationServices {
    @Autowired
    ReservationActRepository reservationActRepository;

    @Autowired
    MaisonService maisonService;

    @Autowired
    UserService userService;

    //compter le nombre des jours
    public int daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    //verifier la date si elle disponible
    public boolean exist(Date start, Date end,long idM) {

        boolean reserv = reservationActRepository.fromDate(start,end,idM).isEmpty();

        if (reserv ==true) {
            System.out.println("true"+reserv);
            return true;
        } else {
            System.out.println("false"+reserv);
            return false;
        }
    }


//******************************USER_API********************************************//

    //add Reservation home for USER
    public ReservationManagement reservationHome(ReservationHomePostDTO reservationDTO) {
        //verifier si l'id du maison entré est valide

        long idM= reservationDTO.getMaison();
        boolean findHome=maisonService.VerifyHome((idM));
        //si id home valide
        if (findHome == true) {

            //verifier la date si elle disponible
            boolean dateExist= exist(reservationDTO.getDebutRes(),reservationDTO.getEndRes(),reservationDTO.getMaison());
            if (dateExist == true){
                //get USERID from MS User to Save In Reservation
                Long userId=userService.getUserByIdOther(reservationDTO.getCustomerId()).getUserId();

                //tester l'id user
                if (userId != null){
                    //instancier une reservation
                    ReservationManagement reservationManagement = new ReservationManagement();

                    //calculer et enregistrer le nombre des jours
                    int nbJour= daysBetween(reservationDTO.getDebutRes(),reservationDTO.getEndRes());
                    reservationManagement.setNumOfDays(nbJour);

                    //enreg id maison
                    MaisonDTOMSA dtoms = maisonService.sendHomeToMSActivity(idM);
                    reservationManagement.setIdMaison(idM);

                    //calculer et enregister le montant a payer
                    int montant = nbJour * dtoms.getPrixResMaison();
                    reservationManagement.setPaymentPrice(montant);

                    reservationManagement.setCustomerId(userId);

                    //enreg le token sended in front
                    reservationManagement.setAddToken(reservationDTO.getAddToken());

                    //enregister les date
                    reservationManagement.setDebutRes(reservationDTO.getDebutRes());
                    reservationManagement.setEndRes(reservationDTO.getEndRes());

                    //etat de la reservation
                    reservationManagement.setReserveStatus("InProgress");

                    //enregister chambre 0
                    reservationManagement.setIdChambre(0);

                    //une reservation de maison
                    reservationManagement.setType("maison");

                    return reservationActRepository.save(reservationManagement);
                }else {
                    System.out.println("id user n'existe pas");
                    return null;
                }

            }else {
                System.out.println("date deja occupee");
                return null;
            }
        } else {
            System.out.println("id home n'existe pas");
            return null;
        }
    }


//******************************Admin_API********************************************//



//******************************Activity_API********************************************//




//******************************Maison_API********************************************//



}

