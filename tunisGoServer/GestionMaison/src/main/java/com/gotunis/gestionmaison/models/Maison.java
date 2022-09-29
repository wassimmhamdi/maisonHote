package com.gotunis.gestionmaison.models;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;


//import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

//creation table maison dan la base de données

@Entity
@Table(name = "maisons")
@Data

//creation du classe maison
public class Maison {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long idUser;

    @Size(max = 50)
    private String nomMaison;

    @Size(max = 20)
    private String regionMaison;

    @Size(max = 200)
    private String adresseMaison;

    @Size(max = 500)
    private String descreptionMaison;

    private int prixResMaison;

    private int nbrChambre;

    private int SalleDeBain;

    private int capacitéTotale;

    private boolean approuver;

    private boolean isChambresRes;

    private boolean isHouseRes;



    @OneToMany(mappedBy = "maison", fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Image> images;


    @OneToMany(mappedBy = "maisonId", fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL)

    private List<Chambre> chambre;


    private String addHouseToken;

    public Maison() {
    }

    public Maison(
            @Size(max = 50) String nom_maison,
            @Size(max = 20) String region_maison,
            @Size(max = 200) String adresse_maison,
            @Size(max = 500) String descreption_maison,
            int prixRes_maison,

            String addHouseToken,

            int nbrChambre,

            List<Image> images)
            {
                this.nomMaison = nom_maison;
                this.regionMaison = region_maison;
                this.adresseMaison = adresse_maison;
               this.descreptionMaison = descreption_maison;
               this.prixResMaison = prixRes_maison;

               this.addHouseToken = addHouseToken;

               this.nbrChambre = nbrChambre;

                this.images = images;
            }

    public Maison(long id, long idUser, @Size(max = 50) String nomMaison, @Size(max = 20) String regionMaison, @Size(max = 200) String adresseMaison, @Size(max = 500) String descreptionMaison, int prixResMaison, int nbrChambre, int salleDeBain, int capacitéTotale, boolean approuver, boolean isChambresRes, boolean isHouseRes, List<Image> images, List<Chambre> chambre) {
        this.id = id;
        this.idUser = idUser;
        this.nomMaison = nomMaison;
        this.regionMaison = regionMaison;
        this.adresseMaison = adresseMaison;
        this.descreptionMaison = descreptionMaison;
        this.prixResMaison = prixResMaison;
        this.nbrChambre = nbrChambre;
        this.SalleDeBain = salleDeBain;
        this.capacitéTotale = capacitéTotale;
        this.approuver = approuver;
        this.isChambresRes = isChambresRes;
        this.isHouseRes = isHouseRes;
        this.images = images;
        this.chambre = chambre;
    }

    public String getAddHouseToken() {
        return addHouseToken;
    }

    public void setAddHouseToken(String addHouseToken) {
        this.addHouseToken = addHouseToken;
    }
}
