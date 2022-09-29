package com.gotunis.gestionactivite.dto.getMicroMaison;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gotunis.gestionactivite.model.Convention;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaisonDTOMSA {
    @Id
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
