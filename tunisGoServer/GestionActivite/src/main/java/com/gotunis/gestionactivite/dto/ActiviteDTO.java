package com.gotunis.gestionactivite.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiviteDTO {

    private Long id;

    private long idClient;

    private String name;

    private String description;

    private String type;

    private String region;

    private Double prix;

    private long maxParticipants;

    private long minParticipants;

    private List<String> images;
}
