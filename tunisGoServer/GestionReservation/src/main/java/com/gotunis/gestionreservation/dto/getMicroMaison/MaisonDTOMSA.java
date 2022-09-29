package com.gotunis.gestionreservation.dto.getMicroMaison;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaisonDTOMSA {

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

}
