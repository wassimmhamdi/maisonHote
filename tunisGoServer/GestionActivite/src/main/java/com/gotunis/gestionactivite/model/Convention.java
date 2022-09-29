package com.gotunis.gestionactivite.model;

import com.fasterxml.jackson.annotation.*;
import com.gotunis.gestionactivite.dto.getMicroMaison.MaisonDTOMSA;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Convention")
public class Convention {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   // private Long idMaison;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinColumn(nullable = false)
    private MaisonDTOMSA maison;

    @Column(name = "approve")
    private boolean approve;

    @Column(name = "propMaison")
    private boolean propMaison;

    @Column(name = "propAct")
    private boolean propAct;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Activite activite;


}
