package com.oc.projets.siteemploiback.service;

import com.oc.projets.siteemploiback.conversion_dto_entity.ConversionAdresse;
import com.oc.projets.siteemploiback.dto.AdresseDTO;
import com.oc.projets.siteemploiback.entity.Adresse;
import com.oc.projets.siteemploiback.exception.AdresseException;
import com.oc.projets.siteemploiback.exception.ResourceNotFoundException;
import com.oc.projets.siteemploiback.repository.AdresseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RelationNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdresseService {

    @Autowired
    private AdresseRepository adresseRepository;

    @Autowired
    private ConversionAdresse conversionAdresse;

    public AdresseDTO create(AdresseDTO adresseDTO) throws AdresseException {
        Adresse adresse = this.conversionAdresse.convertToEntity(adresseDTO);
        try {
            adresse = this.adresseRepository.save(adresse);
            adresseDTO = this.conversionAdresse.convertToDTO(adresse);
            return adresseDTO;
        } catch (Exception e) {
            throw new AdresseException("Problème lors de la création de l'adresse : " + e);
        }

    }

    public Adresse findById(Long id) {
        return this.adresseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Adresse", "id", id));
    }

    public AdresseDTO findByIdDTO(Long id) throws ResourceNotFoundException {
        try {
            Adresse adresse = this.findById(id);
            return this.conversionAdresse.convertToDTO(adresse);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Adresse","id",id);
        }
    }

    public List<AdresseDTO> getAll() {
        List<Adresse> adresseList = this.adresseRepository.findAll();
        List<AdresseDTO> adresseDTOList = adresseList.stream().map(adresse -> this.conversionAdresse.convertToDTO(adresse)).collect(Collectors.toList());
        return adresseDTOList;
    }

    public AdresseDTO update(AdresseDTO adresseDTO) {
        Adresse adresse = this.conversionAdresse.convertToEntity(adresseDTO);
        adresse = this.adresseRepository.save(adresse);
        adresseDTO = this.conversionAdresse.convertToDTO(adresse);
        return adresseDTO;
    }

    public void delete(AdresseDTO adresseDTO){
        Adresse adresse = this.conversionAdresse.convertToEntity(adresseDTO);
        this.adresseRepository.delete(adresse);
    }

    public void deleteById(Long id) {
        this.adresseRepository.deleteById(id);
    }
}
