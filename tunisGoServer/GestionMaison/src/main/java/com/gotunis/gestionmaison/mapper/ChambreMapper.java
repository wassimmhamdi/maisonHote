package com.gotunis.gestionmaison.mapper;

import com.gotunis.gestionmaison.models.Chambre;
import com.gotunis.gestionmaison.models.Image;
import com.gotunis.gestionmaison.models.dto.ChambreDTO;
import com.gotunis.gestionmaison.models.dto.ChambreSendDTO;
import com.gotunis.gestionmaison.models.dto.MaisonDTO;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class ChambreMapper {

    public ChambreDTO mapChambreToDto(Chambre entity) {
        ChambreDTO res = new ChambreDTO();
        if (entity != null) {
            res.setId(entity.getId());
            res.setType(entity.getType());
            res.setNbLits(entity.getNbLits());
            res.setNbPersonne(entity.getNbPersonne());
            res.setPrixLogementSimple(entity.getPrixLogementSimple());
            res.setPrixLogementPD(entity.getPrixLogementPD());
            res.setPrixLogementDP(entity.getPrixLogementDP());
            res.setPrixLogementPC(entity.getPrixLogementPC());
            res.setMaison_id(entity.getMaisonId().getId());
            res.setImages(convertImages(entity.getImages()));

        }
        return res;
    }

    public ChambreSendDTO mapChambreToDtoRes(Chambre entity) {
        ChambreSendDTO res = new ChambreSendDTO();
        if (entity != null) {
            res.setId(entity.getId());
            res.setType(entity.getType());
            res.setNbLits(entity.getNbLits());
            res.setNbPersonne(entity.getNbPersonne());
            res.setPrixLogementSimple(entity.getPrixLogementSimple());
            res.setPrixLogementPD(entity.getPrixLogementPD());
            res.setPrixLogementDP(entity.getPrixLogementDP());
            res.setPrixLogementPC(entity.getPrixLogementPC());
            res.setMaison_id(entity.getMaisonId().getId());
        }
        return res;
    }

    private List<String> convertImages(List<Image> images) {
        List<String> res = null;
        if (images != null && !images.isEmpty()) {
            res = images.stream().map(img -> {
                String encodedString = Base64.getEncoder().encodeToString(img.getData());
                String image = ("data:image/jpeg;base64," + encodedString);
                return image;
            }).collect(Collectors.toList());
        }
        return res;
    }
}