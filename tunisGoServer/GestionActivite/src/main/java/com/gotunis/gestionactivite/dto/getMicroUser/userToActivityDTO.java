package com.gotunis.gestionactivite.dto.getMicroUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class userToActivityDTO {
    private Long userId;

    private String nom;

    private String prenom;

    private String numTel;
}
