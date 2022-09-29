package com.gotunis.gestionactivite.mapper;

import com.gotunis.gestionactivite.model.Activite;
import com.gotunis.gestionactivite.model.FileDB;
import com.gotunis.gestionactivite.dto.ActiviteDTO;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class ActivitieMapper {

    public ActiviteDTO mapToDto(Activite entity) {
        ActiviteDTO res = new ActiviteDTO();

        if (entity != null) {
            System.out.println("in activitie mapper "+res.getName());
            res.setId(entity.getId());
            res.setName(entity.getName());
            res.setDescription(entity.getDescription());
            res.setIdClient(entity.getIdClient());
            res.setType(entity.getType());
            res.setRegion(entity.getRegion());
            res.setPrix(entity.getPrix());
            res.setMaxParticipants(entity.getMaxParticipants());
            res.setMinParticipants(entity.getMinParticipants());
            res.setImages(convertImages(entity.getImages()));
        }
        return res;
    }

    private List<String> convertImages(List<FileDB> images) {
        List<String> res = null;
        if (images != null & !images.isEmpty()) {
            res = images.stream().map(img -> {
                String encodedString = Base64.getEncoder().encodeToString(img.getData());
                String image = ("data:image/jpeg;base64," + encodedString);
                return image;
            }).collect(Collectors.toList());
        }
        return res;
    }
}
