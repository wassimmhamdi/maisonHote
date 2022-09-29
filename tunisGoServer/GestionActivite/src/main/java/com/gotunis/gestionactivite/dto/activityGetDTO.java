package com.gotunis.gestionactivite.dto;

import com.gotunis.gestionactivite.model.Convention;
import com.gotunis.gestionactivite.model.FileDB;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class activityGetDTO {

    private Long id;

    private String name;

    private String description;

    private String type;

    private String region;

    private Double prix;

    private long maxParticipants;

    private long minParticipants;

    private boolean published;

}
