package com.gotunis.gestionactivite.dto;

import lombok.Data;

@Data
public class activityPostDTO {

    private long idClient;

    private String name;

    private String description;

    private String type;

    private String region;

    private Double prix;

    private long maxParticipants;

    private long minParticipants;

    private boolean published;

    private String addActToken;
}
