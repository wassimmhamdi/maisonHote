package com.gotunis.gestionreservation.dto.getMicroActivity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiviteDTO {

    private long id;

    private long idClient;

    private String name;

    private String description;

    private String type;

    private long max_participants;

    private long min_participants;

    private List<String> images;
}
