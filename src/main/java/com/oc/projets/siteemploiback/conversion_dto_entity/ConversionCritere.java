package com.oc.projets.siteemploiback.conversion_dto_entity;

import com.oc.projets.siteemploiback.dto.CritereDTO;
import com.oc.projets.siteemploiback.entity.Critere;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConversionCritere {

    @Autowired
    private ModelMapper modelMapper;

    public CritereDTO convertToDTO(Critere critere) {
        return this.modelMapper.map(critere,CritereDTO.class);
    }

    public Critere convertToEntity(CritereDTO critereDTO) {
        return this.modelMapper.map(critereDTO,Critere.class);
    }
}
