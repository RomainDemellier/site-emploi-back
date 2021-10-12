package com.oc.projets.siteemploiback.conversion_dto_entity;

import com.oc.projets.siteemploiback.dto.UtilisateurDTO;
import com.oc.projets.siteemploiback.entity.Utilisateur;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConversionUtilisateur {

    @Autowired
    private ModelMapper modelMapper;

    public UtilisateurDTO convertToDTO(Utilisateur utilisateur) {
        return modelMapper.map(utilisateur,UtilisateurDTO.class);
    }

    public Utilisateur convertToEntity(UtilisateurDTO utilisateurDTO) {
        return modelMapper.map(utilisateurDTO,Utilisateur.class);
    }
}
