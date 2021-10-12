package com.oc.projets.siteemploiback.conversion_dto_entity;

import com.oc.projets.siteemploiback.dto.AnnonceDTO;
import com.oc.projets.siteemploiback.entity.Annonce;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConversionAnnonce {

    @Autowired
    ModelMapper modelMapper;

    public AnnonceDTO convertToDTO(Annonce annonce) {
        return this.modelMapper.map(annonce,AnnonceDTO.class);
    }

    public Annonce convertToEntity(AnnonceDTO annonceDTO) {
        return this.modelMapper.map(annonceDTO,Annonce.class);
    }
}
