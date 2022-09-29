package com.gotunis.gestionmaison.mapper;

import com.gotunis.gestionmaison.models.Image;
import com.gotunis.gestionmaison.models.Maison;
import com.gotunis.gestionmaison.models.dto.MaisonDTO;
import com.gotunis.gestionmaison.models.dto.sendMicroActivity.MaisonDTOMSA;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class MaisonMapper {

    public MaisonDTO mapToDto(Maison entity) {
        MaisonDTO res = new MaisonDTO();
        if (entity != null) {
            res.setId(entity.getId());
            res.setNomMaison(entity.getNomMaison());
            res.setAdresseMaison(entity.getAdresseMaison());
            res.setDescreptionMaison(entity.getDescreptionMaison());
            res.setPrixResMaison(entity.getPrixResMaison());
            res.setRegionMaison(entity.getRegionMaison());
            res.setApprouver(entity.isApprouver());
            res.setImages(convertImages(entity.getImages()));
            res.setNbrChambre(entity.getNbrChambre());
            res.setIdUser(entity.getIdUser());
            res.setSalleDeBain(entity.getSalleDeBain());
            res.setCapacitéTotale(entity.getCapacitéTotale());
            

        }
        return res;
    }

    //Convertie une liste des Maisons en Liste MaisonDTO
    public List<MaisonDTO> mapToListDto(List<Maison> maisons) {
            List<MaisonDTO> res = maisons.stream().map(maison -> {
                return mapToDto(maison);
            }).collect(Collectors.toList());
        return res;
    }

    public MaisonDTOMSA mapToDtoAct(Maison entity) {
        MaisonDTOMSA maisonDTOMSA = new MaisonDTOMSA();
        if (entity != null) {
            maisonDTOMSA.setId(entity.getId());
            maisonDTOMSA.setNomMaison(entity.getNomMaison());
            maisonDTOMSA.setIdUser(entity.getIdUser());
            maisonDTOMSA.setRegionMaison(entity.getRegionMaison());
            maisonDTOMSA.setAdresseMaison(entity.getAdresseMaison());
            maisonDTOMSA.setDescreptionMaison(entity.getDescreptionMaison());
            maisonDTOMSA.setPrixResMaison(entity.getPrixResMaison());
            maisonDTOMSA.setNbrChambre(entity.getNbrChambre());
            maisonDTOMSA.setSalleDeBain(entity.getSalleDeBain());
            maisonDTOMSA.setCapacitéTotale(entity.getCapacitéTotale());
            maisonDTOMSA.setApprouver(entity.isApprouver());
            maisonDTOMSA.setChambresRes(entity.isChambresRes());
            maisonDTOMSA.setHouseRes(entity.isHouseRes());
        }
        return maisonDTOMSA;
    }

    private List<String> convertImages(List<Image> images) {
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
