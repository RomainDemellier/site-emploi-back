package com.oc.projets.siteemploiback.service;

import com.oc.projets.siteemploiback.conversion_dto_entity.ConversionPoste;
import com.oc.projets.siteemploiback.conversion_dto_entity.ConversionSecteur;
import com.oc.projets.siteemploiback.dto.PosteDTO;
import com.oc.projets.siteemploiback.dto.SecteurDTO;
import com.oc.projets.siteemploiback.entity.Poste;
import com.oc.projets.siteemploiback.entity.Secteur;
import com.oc.projets.siteemploiback.exception.ResourceNotFoundException;
import com.oc.projets.siteemploiback.repository.SecteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SecteurService {

    @Autowired
    private SecteurRepository secteurRepository;

    @Autowired
    private ConversionSecteur conversionSecteur;

    @Autowired
    private PosteService posteService;

    @Autowired
    private ConversionPoste conversionPoste;

    public SecteurDTO create(SecteurDTO secteurDTO) {
        Secteur secteur = this.conversionSecteur.convertToEntity(secteurDTO);
        secteur = this.secteurRepository.save(secteur);
        secteurDTO = this.conversionSecteur.convertToDTO(secteur);
        return secteurDTO;
    }

    public Secteur findById(Long id) {
        return this.secteurRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Secteur","id",id));
    }

    public SecteurDTO findByIdDTO(Long id) {
        try {
            Secteur secteur = this.findById(id);
            SecteurDTO secteurDTO = this.conversionSecteur.convertToDTO(secteur);
            return secteurDTO;
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Secteur","id",id);
        }
    }

    public List<SecteurDTO> getAll() {
        List<Secteur> secteurList = this.secteurRepository.findAll();
        List<SecteurDTO> secteurDTOList = secteurList.stream().map((secteur) -> this.conversionSecteur.convertToDTO(secteur)).collect(Collectors.toList());
        return secteurDTOList;
    }

    public SecteurDTO update(SecteurDTO secteurDTO) {
        Secteur secteur = this.conversionSecteur.convertToEntity(secteurDTO);
        secteur = this.secteurRepository.save(secteur);
        secteurDTO = this.conversionSecteur.convertToDTO(secteur);
        return secteurDTO;
    }

    public void delete(SecteurDTO secteurDTO) {
        Secteur secteur = this.conversionSecteur.convertToEntity(secteurDTO);
        this.secteurRepository.delete(secteur);
    }

    public void deleteById(Long id) {
        this.secteurRepository.deleteById(id);
    }

    public SecteurDTO addPoste(Long id, PosteDTO posteDTO) throws Exception {
        try {
            Secteur secteur = this.findById(id);
            SecteurDTO secteurDTO = this.conversionSecteur.convertToDTO(secteur);
            posteDTO.setSecteur(secteurDTO);
            this.posteService.create(posteDTO);
            secteurDTO = this.conversionSecteur.convertToDTO(secteur);
            return secteurDTO;
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Secteur","id",id);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public SecteurDTO deletePoste(Long secteurId, Long posteId) throws Exception {
        try {
            Secteur secteur = this.findById(secteurId);
            List<Poste> posteList = secteur.getPosteList();
            posteList.removeIf(poste -> poste.getId().equals(posteId));
            secteur.setPosteList(posteList);
            secteur = this.secteurRepository.save(secteur);
            SecteurDTO secteurDTO = this.conversionSecteur.convertToDTO(secteur);
            return secteurDTO;
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Secteur","id",secteurId);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public List<PosteDTO> getPostesOfSecteur(Long id) throws Exception {
        try {
            Secteur secteur = this.findById(id);
            List<Poste> posteList = secteur.getPosteList();
            List<PosteDTO> posteDTOList = posteList.stream().map(poste -> this.conversionPoste.convertToDTO(poste)).collect(Collectors.toList());
            return posteDTOList;
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Secteur","id",id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
