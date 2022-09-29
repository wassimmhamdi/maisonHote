package com.gotunis.gestionmaison.models;

import javax.persistence.*;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Table(name = "images")

public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nameImage;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "maison_id", nullable = true, referencedColumnName = "id")

    private Maison maison;


    @ManyToOne
    @JoinColumn(name = "chambre_id", nullable = true, referencedColumnName = "id")

    private Chambre chambre;


    private String type;

    @Lob
    private byte[] data;

    public Image() {
    }

    public Image(String nameImage, Maison maison,Chambre chambre, String type, byte[] data) {
        this.nameImage = nameImage;
        this.maison = maison;
        this.chambre = chambre;
        this.type = type;
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public Maison getMaison() {
        return maison;
    }

    public void setMaison(Maison maison) {
        this.maison = maison;
    }

    public Chambre getChambre() {return chambre;}

    public void setChambre(Chambre chambre) {this.chambre = chambre;}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
