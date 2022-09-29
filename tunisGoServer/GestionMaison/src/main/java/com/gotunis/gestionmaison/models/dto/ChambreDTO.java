package com.gotunis.gestionmaison.models.dto;

import com.gotunis.gestionmaison.models.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChambreDTO {
    private long id;

    private String type;

    private float prixLogementSimple;

    private float prixLogementPD;

    private float prixLogementDP;

    private float prixLogementPC;

    private int nbPersonne;

    private int nbLits;

    private long maison_id;

    private List<String> images;

}
