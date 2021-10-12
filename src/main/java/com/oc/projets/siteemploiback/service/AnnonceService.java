package com.oc.projets.siteemploiback.service;

import com.oc.projets.siteemploiback.conversion_dto_entity.ConversionAnnonce;
import com.oc.projets.siteemploiback.conversion_dto_entity.ConversionUtilisateur;
import com.oc.projets.siteemploiback.dto.AnnonceDTO;
import com.oc.projets.siteemploiback.dto.UtilisateurDTO;
import com.oc.projets.siteemploiback.entity.Annonce;
import com.oc.projets.siteemploiback.entity.Utilisateur;
import com.oc.projets.siteemploiback.exception.AnnonceException;
import com.oc.projets.siteemploiback.exception.ResourceNotFoundException;
import com.oc.projets.siteemploiback.repository.AnnonceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnnonceService {

    @Autowired
    private AnnonceRepository annonceRepository;

    @Autowired
    private RecruteurService recruteurService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private ConversionAnnonce conversionAnnonce;

    @Autowired
    private ConversionUtilisateur conversionUtilisateur;

    public AnnonceDTO create(AnnonceDTO annonceDTO) throws AnnonceException {
        Long recruteurId = annonceDTO.getRecruteur().getId();
        if(! recruteurService.isRecruteur(recruteurId)) {
            throw new AnnonceException("Seul un recruteur peut créer une annonce.");
        }
//        annonceDTO.setPublie(false);
        try {
            Annonce annonce = this.conversionAnnonce.convertToEntity(annonceDTO);
            annonce = this.annonceRepository.save(annonce);
            annonceDTO = this.conversionAnnonce.convertToDTO(annonce);
            return annonceDTO;
        } catch (Exception e) {
            throw new AnnonceException(e.getMessage());
        }
    }

    public Annonce findById(Long id) {
        return this.annonceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Annonce","id",id));
    }

    public AnnonceDTO findByIdDTO(Long id) {
        try {
            Annonce annonce = this.findById(id);
            AnnonceDTO annonceDTO = this.conversionAnnonce.convertToDTO(annonce);
            return annonceDTO;
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Annonce","id",id);
        }
    }

    public List<AnnonceDTO> getAll() {
        List<Annonce> annonceList = this.annonceRepository.findAll();
        List<AnnonceDTO> annonceDTOList = annonceList.stream().map(annonce -> this.conversionAnnonce.convertToDTO(annonce)).collect(Collectors.toList());
        return annonceDTOList;
    }

    public Annonce update(Annonce annonce) throws Exception {
        try {
            annonce = this.annonceRepository.save(annonce);
            return annonce;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public AnnonceDTO updateDTO(AnnonceDTO annonceDTO) throws Exception {
        try {
            Annonce annonce = this.conversionAnnonce.convertToEntity(annonceDTO);
            annonce = this.annonceRepository.save(annonce);
            annonceDTO = this.conversionAnnonce.convertToDTO(annonce);
            return annonceDTO;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public AnnonceDTO publier(Long id) throws Exception {
        try {
            Annonce annonce = this.findById(id);
            annonce.setPublie(true);
            annonce = this.annonceRepository.save(annonce);
            AnnonceDTO annonceDTO = this.conversionAnnonce.convertToDTO(annonce);
            return annonceDTO;
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Annonce","id",id);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public AnnonceDTO depublier(Long id) throws Exception {
        try {
            Annonce annonce = this.findById(id);
            annonce.setPublie(false);
            annonce = this.annonceRepository.save(annonce);
            AnnonceDTO annonceDTO = this.conversionAnnonce.convertToDTO(annonce);
            return annonceDTO;
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Annonce","id",id);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public AnnonceDTO findAndAddOneUtilisateur(Long id,UtilisateurDTO utilisateurDTO) throws AnnonceException {
        Annonce annonce = this.findById(id);
        try {
            annonce = this.addOneUtilisateur(annonce,utilisateurDTO);
            AnnonceDTO annonceDTO = this.conversionAnnonce.convertToDTO(annonce);
            return annonceDTO;
        } catch (AnnonceException e) {
            throw new AnnonceException(e.getMessage());
        }

    }

    public AnnonceDTO findAndAddSeveralUtilisateur(Long id, List<UtilisateurDTO> utilisateurListDTO) throws AnnonceException {
        Annonce annonce = this.findById(id);
        for(UtilisateurDTO u: utilisateurListDTO) {
            this.addOneUtilisateur(annonce,u);
        }
        AnnonceDTO annonceDTO = this.conversionAnnonce.convertToDTO(annonce);
        return annonceDTO;
    }

    public Annonce addOneUtilisateur(Annonce annonce,UtilisateurDTO utilisateurDTO) throws AnnonceException {
        List<Utilisateur> utilisateurListWhoRead = annonce.getUtilisateurListWhoReadNotified();
        Utilisateur utilisateur = this.conversionUtilisateur.convertToEntity(utilisateurDTO);
        if(!utilisateurListWhoRead.stream().anyMatch(u -> u.getId().equals(utilisateur.getId()))) {
            annonce.addUtilisateurListHowReadNotified(utilisateur);
            annonce = this.annonceRepository.save(annonce);
            return annonce;
        } else {
            throw new AnnonceException("Cet utilisateur a déjà consulté cette annonce");
        }
    }

    public void deleteById(Long id) {
        this.annonceRepository.deleteById(id);
    }

    public void delete(AnnonceDTO annonceDTO) {
        Annonce annonce = this.conversionAnnonce.convertToEntity(annonceDTO);
        this.annonceRepository.delete(annonce);
    }

    public List<AnnonceDTO> findAnnonceNotReadByUtilisateurDTOById(Long id) {
        List<Annonce> annonceList = this.findAnnonceNotReadByUtilisateurById(id);
        List<AnnonceDTO> annonceDTOList = annonceList.stream().map(annonce -> this.conversionAnnonce.convertToDTO(annonce)).collect(Collectors.toList());
        return annonceDTOList;
    }

    public List<Annonce> findAnnonceNotReadByUtilisateurById(Long id) {
        Utilisateur utilisateur = this.utilisateurService.findById(id);
        return this.annonceRepository.findByUtilisateurListWhoReadNotifiedNotContains(utilisateur);
    }

    public List<Annonce> findAnnonceNotReadByUtilisateur(Utilisateur utilisateur) {
        return this.annonceRepository.findByUtilisateurListWhoReadNotifiedNotContains(utilisateur);
    }

    public AnnonceDTO emptyListUtilisateurs(Long id) throws Exception {
        try {
            Annonce annonce = this.findById(id);
            annonce.setUtilisateurListWhoReadNotified(new ArrayList<>());
            annonce = this.annonceRepository.save(annonce);
            AnnonceDTO annonceDTO = this.conversionAnnonce.convertToDTO(annonce);
            return annonceDTO;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
