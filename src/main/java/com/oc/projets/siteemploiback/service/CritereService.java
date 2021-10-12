package com.oc.projets.siteemploiback.service;

import com.oc.projets.siteemploiback.conversion_dto_entity.ConversionCritere;
import com.oc.projets.siteemploiback.dto.CritereDTO;
import com.oc.projets.siteemploiback.entity.Critere;
import com.oc.projets.siteemploiback.entity.Utilisateur;
import com.oc.projets.siteemploiback.exception.ResourceNotFoundException;
import com.oc.projets.siteemploiback.repository.CritereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CritereService {

    @Autowired
    private CritereRepository critereRepository;

    @Autowired
    private ConversionCritere conversionCritere;

    @Autowired
    private AnnonceService annonceService;

    public CritereDTO create(CritereDTO critereDTO) throws Exception {
        try {
            Critere critere = this.conversionCritere.convertToEntity(critereDTO);
            System.out.println(critere.toString());
            critere = this.critereRepository.save(critere);
            critereDTO = this.conversionCritere.convertToDTO(critere);
            return critereDTO;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Critere findById(Long id) {
        return this.critereRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Critere","id",id));
    }

    public CritereDTO findByIdDTO(Long id) {
        try {
            Critere critere = this.findById(id);
            CritereDTO critereDTO = this.conversionCritere.convertToDTO(critere);
            return critereDTO;
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Critere","id",id);
        }
    }

    public List<CritereDTO> getAll() throws Exception {
        try {
            List<Critere> critereList = this.critereRepository.findAll();
            List<CritereDTO> critereDTOList = critereList.stream().map(critere -> this.conversionCritere.convertToDTO(critere)).collect(Collectors.toList());
            return critereDTOList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public CritereDTO update(CritereDTO critereDTO) throws Exception {
        try {
            Critere critere = this.conversionCritere.convertToEntity(critereDTO);
            critere = this.critereRepository.save(critere);
            critereDTO = this.conversionCritere.convertToDTO(critere);
            return critereDTO;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void delete(CritereDTO critereDTO) {
        Critere critere = this.conversionCritere.convertToEntity(critereDTO);
        this.critereRepository.delete(critere);
    }

    public void deleteById(Long id) {
        this.critereRepository.deleteById(id);
    }

    public Critere getByUtilisateur(Utilisateur utilisateur) {
        return this.critereRepository.findByUtilisateur(utilisateur);
    }

    public CritereDTO getByUtilisateurDTO(Utilisateur utilisateur) {
        Critere critere = this.getByUtilisateur(utilisateur);
        CritereDTO critereDTO = this.conversionCritere.convertToDTO(critere);
        return critereDTO;
    }
}
