package com.gotunis.gestionmaison.models.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaisonDTO {

    private long id;

    private String nomMaison;

    private long idUser;

    private String regionMaison;

    private String adresseMaison;

    private String descreptionMaison;

    private int prixResMaison;

    private int nbrChambre;

    private int SalleDeBain;

    private int capacitéTotale;

    private boolean approuver;

    private boolean isChambresRes;

    private boolean isHouseRes;

    private List<String> images;

}
