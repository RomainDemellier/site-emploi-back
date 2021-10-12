package com.oc.projets.siteemploiback.conversion_dto_entity;

import com.oc.projets.siteemploiback.dto.SecteurDTO;
import com.oc.projets.siteemploiback.entity.Secteur;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConversionSecteur {

    @Autowired
    private ModelMapper modelMapper;

    public SecteurDTO convertToDTO(Secteur secteur) {
        return this.modelMapper.map(secteur,SecteurDTO.class);
    }

    public Secteur convertToEntity(SecteurDTO secteurDTO) {
        return this.modelMapper.map(secteurDTO,Secteur.class);
    }
}
