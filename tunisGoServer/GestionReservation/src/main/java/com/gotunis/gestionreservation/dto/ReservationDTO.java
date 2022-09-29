package com.gotunis.gestionreservation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gotunis.gestionreservation.dto.getMicroMaison.ChambreDTO;
import com.gotunis.gestionreservation.dto.getMicroMaison.MaisonDTOMSA;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private Long id;

    private Long customerId;

    private String reserveStatus;

    private double  paymentPrice;

    private String type;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date debutRes;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date endRes;

    private int numOfDays;

    private long idChambre;

    private long idMaison;
}
