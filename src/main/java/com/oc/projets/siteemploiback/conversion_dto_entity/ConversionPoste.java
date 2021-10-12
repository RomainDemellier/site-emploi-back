package com.oc.projets.siteemploiback.conversion_dto_entity;

import com.oc.projets.siteemploiback.dto.PosteDTO;
import com.oc.projets.siteemploiback.entity.Poste;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConversionPoste {

    @Autowired
    private ModelMapper modelMapper;

    public PosteDTO convertToDTO(Poste poste) {
        return this.modelMapper.map(poste, PosteDTO.class);
    }

    public Poste convertToEntity(PosteDTO posteDTO) {
        return this.modelMapper.map(posteDTO,Poste.class);
    }
}
