package com.gotunis.gestionreservation.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(	name = "ReservationManagement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private String reserveStatus;

    private int numOfDays;

    private double  paymentPrice;

    private String type;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date debutRes;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date endRes;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinColumn(nullable = true)
    private ReservationActivity reservationActivity;

    private long idChambre;

    private long idMaison;

    private String addToken;

}
