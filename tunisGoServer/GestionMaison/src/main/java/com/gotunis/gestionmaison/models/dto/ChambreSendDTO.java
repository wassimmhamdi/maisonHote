package com.gotunis.gestionmaison.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChambreSendDTO {
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
