package com.oc.projets.siteemploiback.conversion_dto_entity;

import com.oc.projets.siteemploiback.dto.AdresseDTO;
import com.oc.projets.siteemploiback.entity.Adresse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConversionAdresse {

    @Autowired
    private ModelMapper modelMapper;

    public AdresseDTO convertToDTO(Adresse adresse) {
        return modelMapper.map(adresse,AdresseDTO.class);
    }

    public Adresse convertToEntity(AdresseDTO adresseDTO) {
        return modelMapper.map(adresseDTO,Adresse.class);
    }
}
