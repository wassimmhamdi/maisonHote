package com.gotunis.gestionreservation.mapper;

import com.gotunis.gestionreservation.dto.ReservationDTO;
import com.gotunis.gestionreservation.models.ReservationManagement;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationMapper {

    public ReservationDTO mapToDto(ReservationManagement entity) {
        ReservationDTO reservation = new ReservationDTO();
        if (entity != null) {
            reservation.setId(entity.getId());
            reservation.setCustomerId(entity.getCustomerId());
            reservation.setReserveStatus(entity.getReserveStatus());
            reservation.setPaymentPrice(entity.getPaymentPrice());
            reservation.setType(entity.getType());
            reservation.setDebutRes(entity.getDebutRes());
            reservation.setEndRes(entity.getEndRes());
            reservation.setNumOfDays(entity.getNumOfDays());
            reservation.setIdMaison(entity.getIdMaison());
            reservation.setIdChambre(entity.getIdChambre());
        }
        return reservation;
    }

    //Convertie en une liste des reservations
    public List<ReservationDTO> mapToListDto(List<ReservationManagement> reservationManagements) {
        List<ReservationDTO> reservationDTOList = reservationManagements.stream().map(reservation -> {
            return mapToDto(reservation);
        }).collect(Collectors.toList());
        return reservationDTOList;
    }
}
