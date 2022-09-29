package com.gotunis.gestionmaison.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
@Table(name = "chambre")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chambre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String type;

    private float prixLogementSimple;

    private float prixLogementPD;

    private float prixLogementDP;

    private float prixLogementPC;

    private int nbPersonne;

    private int nbLits;

    @OneToMany(mappedBy = "chambre", fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Image> images;

    @ManyToOne
    @JoinColumn(name = "maison_id", nullable = false, referencedColumnName = "id")
    private Maison maisonId;

}
