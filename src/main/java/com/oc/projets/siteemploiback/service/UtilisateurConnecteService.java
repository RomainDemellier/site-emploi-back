package com.oc.projets.siteemploiback.service;

import com.oc.projets.siteemploiback.conversion_dto_entity.ConversionUtilisateur;
import com.oc.projets.siteemploiback.dto.UtilisateurDTO;
import com.oc.projets.siteemploiback.entity.Utilisateur;
import com.oc.projets.siteemploiback.repository.UtilisateurRepository;
import com.oc.projets.siteemploiback.security.service.UtilisateurDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurConnecteService {

    private Utilisateur utilisateur = null;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private ConversionUtilisateur conversionUtilisateur;

    public UtilisateurDTO getUtilisateurDTOConnecte() {
        this.utilisateur = this.getUtilisateurConnecte();
        UtilisateurDTO utilisateurDTO = this.conversionUtilisateur.convertToDTO(this.utilisateur);
        return utilisateurDTO;
    }

    public Utilisateur getUtilisateurConnecte() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        utilisateur = this.utilisateurRepository.findByEmail(user.getUsername());
        return utilisateur;
    }
}
