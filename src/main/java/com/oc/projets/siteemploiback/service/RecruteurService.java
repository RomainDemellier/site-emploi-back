package com.oc.projets.siteemploiback.service;

import com.oc.projets.siteemploiback.conversion_dto_entity.ConversionAdresse;
import com.oc.projets.siteemploiback.conversion_dto_entity.ConversionUtilisateur;
import com.oc.projets.siteemploiback.dto.AdresseDTO;
import com.oc.projets.siteemploiback.dto.UtilisateurDTO;
import com.oc.projets.siteemploiback.entity.Adresse;
import com.oc.projets.siteemploiback.entity.Utilisateur;
import com.oc.projets.siteemploiback.exception.ResourceNotFoundException;
import com.oc.projets.siteemploiback.exception.UtilisateurException;
import com.oc.projets.siteemploiback.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.oc.projets.siteemploiback.service.UtilisateurService.getUtilisateurDTO;

@Service
public class RecruteurService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    ConversionUtilisateur conversionUtilisateur;

    @Autowired
    AdresseService adresseService;

    @Autowired
    private ConversionAdresse conversionAdresse;

    public UtilisateurDTO create(UtilisateurDTO utilisateurDTO) throws Exception {
        try {
            utilisateurDTO.setRole("RECRUTEUR");
            utilisateurDTO.getAdresse().setNom("Siège");
            return getUtilisateurDTO(utilisateurDTO, this.conversionUtilisateur, this.utilisateurRepository);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        }
    }

    public Utilisateur findById(Long id) throws ResourceNotFoundException {
        try {
            return this.utilisateurRepository.findByIdAndRole(id,"RECRUTEUR");
        } catch (Exception e) {
            throw new ResourceNotFoundException("Recruteur","id",id);
        }
    }

    public UtilisateurDTO findByIdDTO(Long id) {
        try {
            Utilisateur recruteur = this.findById(id);
            return  this.conversionUtilisateur.convertToDTO(recruteur);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Recruteur","id",id);
        }
    }

    public List<UtilisateurDTO> getAll() {
        List<Utilisateur> recruteurList = this.utilisateurRepository.findAllByRole("RECRUTEUR");
        List<UtilisateurDTO> recruteurDTOList = recruteurList.stream().map(recruteur -> this.conversionUtilisateur.convertToDTO(recruteur)).collect(Collectors.toList());
        return recruteurDTOList;
    }

    public UtilisateurDTO update(UtilisateurDTO recruteurDTO) {
        Utilisateur recruteur = this.conversionUtilisateur.convertToEntity(recruteurDTO);
        recruteur = this.utilisateurRepository.save(recruteur);
        recruteurDTO = this.conversionUtilisateur.convertToDTO(recruteur);
        return recruteurDTO;
    }

    public void delete(UtilisateurDTO recruteurDTO) {
        Utilisateur recruteur = this.conversionUtilisateur.convertToEntity(recruteurDTO);
        this.utilisateurRepository.delete(recruteur);
    }

    public void deleteById(Long id) {
        this.utilisateurRepository.deleteById(id);
    }

    public AdresseDTO updateAdresseSiege(AdresseDTO adresseDTO) {
        adresseDTO = this.adresseService.update(adresseDTO);
        return adresseDTO;
    }

    public UtilisateurDTO addAdresseSecondaire(Long id, AdresseDTO adresseDTO) throws Exception {
        try {
            Adresse adresse = this.conversionAdresse.convertToEntity(adresseDTO);
            Utilisateur recruteur = this.findById(id);
            recruteur.addAdresseSecondaire(adresse);
            recruteur = this.utilisateurRepository.save(recruteur);
            UtilisateurDTO recruteurDTO = this.conversionUtilisateur.convertToDTO(recruteur);
            return recruteurDTO;
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Recruteur","id",id);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public UtilisateurDTO deleteAdresse(Long recruteurId, Long adresseId) {
        Utilisateur recruteur = this.findById(recruteurId);
        List<Adresse> adresseList = recruteur.getAdressesSecondaires();
        adresseList.removeIf(adresse -> adresse.getId().equals(adresseId));
        recruteur.setAdressesSecondaires(adresseList);
        recruteur = this.utilisateurRepository.save(recruteur);
        UtilisateurDTO recruteurDTO = this.conversionUtilisateur.convertToDTO(recruteur);
        return recruteurDTO;
    }

    public Boolean isRecruteur(Long id) {
            Utilisateur utilisateur = this.findById(id);
            if(utilisateur == null) return false;
            return true;
    }
}

