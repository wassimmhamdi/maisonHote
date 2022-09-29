package com.gotunis.gestionreservation.dto.getMicroMaison;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ChambreSendDTO {
    @Id
    private long id;

    private String type;

    private float prixLogementSimple;

    private float prixLogementPD;

    private float prixLogementDP;

    private float prixLogementPC;

    private int nbPersonne;

    private int nbLits;

    private long maison_id;
}
