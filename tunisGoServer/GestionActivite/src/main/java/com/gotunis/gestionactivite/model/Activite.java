package com.gotunis.gestionactivite.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="activite")
public class Activite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private long idClient;

    private String name;

    private String description;

    private String type;

    private String region;

    private Double prix;

    private long maxParticipants;

    private long minParticipants;

    @Column(name = "published")
    private boolean published;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "activite",
            cascade = CascadeType.ALL)
    private List<Convention> conventions  ;

    @JsonManagedReference
    @OneToMany(mappedBy = "activite_File", fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<FileDB> images;

    private String addActToken;

    

}
