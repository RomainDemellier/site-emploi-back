package com.oc.projets.siteemploiback.service;

import com.oc.projets.siteemploiback.conversion_dto_entity.ConversionUtilisateur;
import com.oc.projets.siteemploiback.dto.UtilisateurDTO;
import com.oc.projets.siteemploiback.entity.Utilisateur;
import com.oc.projets.siteemploiback.exception.ResourceNotFoundException;
import com.oc.projets.siteemploiback.exception.UtilisateurException;
import com.oc.projets.siteemploiback.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private ConversionUtilisateur conversionUtilisateur;

    public UtilisateurDTO create(UtilisateurDTO utilisateurDTO) throws UtilisateurException {
        utilisateurDTO.setRole("USER");
        String prenom = utilisateurDTO.getPrenom();
        if(prenom.equals(null) || prenom.isEmpty()) {
            throw new UtilisateurException("Le prénom est requis");
        }
        try {
            return getUtilisateurDTO(utilisateurDTO, this.conversionUtilisateur, this.utilisateurRepository);
        } catch (Exception e) {
            System.out.println(e);
            throw new UtilisateurException(e.getMessage());
        }

    }

    public UtilisateurDTO createAdmin(UtilisateurDTO utilisateurAdminDTO) throws Exception {
        try {
            utilisateurAdminDTO.setRole("ADMIN");
            return getUtilisateurDTO(utilisateurAdminDTO, this.conversionUtilisateur, this.utilisateurRepository);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    static UtilisateurDTO getUtilisateurDTO(UtilisateurDTO utilisateurDTO, ConversionUtilisateur conversionUtilisateur, UtilisateurRepository utilisateurRepository) {
        Utilisateur utilisateur = conversionUtilisateur.convertToEntity(utilisateurDTO);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePwd = bCryptPasswordEncoder.encode(utilisateur.getPassword());
        utilisateur.setPassword(encodePwd);
        utilisateur = utilisateurRepository.save(utilisateur);
        utilisateurDTO = conversionUtilisateur.convertToDTO(utilisateur);
        return utilisateurDTO;
    }

    public Utilisateur findById(Long id) {
        return this.utilisateurRepository.findByIdAndRole(id,"USER");
    }

    public UtilisateurDTO findByIdDTO(Long id) throws ResourceNotFoundException {
        try {
            Utilisateur utilisateur = this.findById(id);
            UtilisateurDTO utilisateurDTO = this.conversionUtilisateur.convertToDTO(utilisateur);
            return utilisateurDTO;
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Utilisateur","id",id);
        }
    }

    public List<UtilisateurDTO> getAllDTO() {
        List<Utilisateur> utilisateurList = this.utilisateurRepository.findAllByRole("USER");
        List<UtilisateurDTO> utilisateurDTOList = utilisateurList.stream().map(utilisateur -> this.conversionUtilisateur.convertToDTO(utilisateur)).collect(Collectors.toList());
        return utilisateurDTOList;
    }

    public List<Utilisateur> getAll() {
        return this.utilisateurRepository.findAllByRole("USER");
    }

    public UtilisateurDTO update(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = this.conversionUtilisateur.convertToEntity(utilisateurDTO);
        utilisateur = this.utilisateurRepository.save(utilisateur);
        utilisateurDTO = this.conversionUtilisateur.convertToDTO(utilisateur);
        return utilisateurDTO;
    }

    public void delete(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = this.conversionUtilisateur.convertToEntity(utilisateurDTO);
        this.utilisateurRepository.delete(utilisateur);
    }

    public void deleteById(Long id) {
        this.utilisateurRepository.deleteById(id);
    }
}
