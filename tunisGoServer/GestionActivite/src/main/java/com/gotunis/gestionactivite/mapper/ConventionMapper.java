package com.gotunis.gestionactivite.mapper;

import com.gotunis.gestionactivite.dto.ConventionDTO;
import com.gotunis.gestionactivite.dto.getMicroMaison.MaisonDTOMSA;
import com.gotunis.gestionactivite.model.Activite;
import com.gotunis.gestionactivite.model.Convention;
import com.gotunis.gestionactivite.repository.ConventionRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConventionMapper {
    @Autowired
    ConventionRepository conventionRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ConventionDTO> getAllConvention() {
        return ((List<Convention>) conventionRepository
                .findAll())
                .stream()
                .map(this::convertToConventionDTO1)
                .collect(Collectors.toList());
    }

    private ConventionDTO convertToConventionDTO(Convention convention) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        ConventionDTO conventionDTO = modelMapper
                .map(convention, ConventionDTO.class);
        return conventionDTO;
    }

    public ConventionDTO convertToConventionDTO1(Convention convention) {
        ConventionDTO conventionDTO = new ConventionDTO();
        if (convention != null) {
            conventionDTO.setId(convention.getId());
            conventionDTO.setApprove(convention.isApprove());

            Activite activite = convention.getActivite();
            conventionDTO.setActivity_id(activite.getId());

            MaisonDTOMSA maisonDTOMSA= convention.getMaison();
            conventionDTO.setIdMaison(maisonDTOMSA.getId());
        }
        return conventionDTO;
    }

    //Convertie en une liste des conventionDTO
    public List<ConventionDTO> mapToListDto(List<Convention> conventions) {
        List<ConventionDTO> res = conventions.stream().map(maison -> {
            return convertToConventionDTO1(maison);
        }).collect(Collectors.toList());
        return res;}

}
