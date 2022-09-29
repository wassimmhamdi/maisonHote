package com.gotunis.gestionactivite.dto;


import com.gotunis.gestionactivite.model.Activite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ConventionDTO {

    private Long id;

    private boolean approve;

    private Long idMaison;

    private Long activity_id;


}
