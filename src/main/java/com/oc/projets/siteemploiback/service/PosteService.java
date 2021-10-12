package com.oc.projets.siteemploiback.service;

import com.oc.projets.siteemploiback.conversion_dto_entity.ConversionPoste;
import com.oc.projets.siteemploiback.dto.PosteDTO;
import com.oc.projets.siteemploiback.entity.Poste;
import com.oc.projets.siteemploiback.exception.ResourceNotFoundException;
import com.oc.projets.siteemploiback.repository.PosteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class PosteService {

    @Autowired
    private PosteRepository posteRepository;

    @Autowired
    private ConversionPoste conversionPoste;

    public PosteDTO create(PosteDTO posteDTO) throws Exception {
        try {
            Poste poste = this.conversionPoste.convertToEntity(posteDTO);
            poste = this.posteRepository.save(poste);
            posteDTO = this.conversionPoste.convertToDTO(poste);
            return posteDTO;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public List<PosteDTO> getAll() {
        List<Poste> posteList = this.posteRepository.findAll();
        List<PosteDTO> posteDTOList = posteList.stream().map(poste -> this.conversionPoste.convertToDTO(poste)).collect(Collectors.toList());
        return posteDTOList;
    }

    public Poste findById(Long id) {
        return this.posteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Poste","id",id));
    }

    public PosteDTO findByIdDTO(Long id) {
        try {
            Poste poste = this.findById(id);
            PosteDTO posteDTO = this.conversionPoste.convertToDTO(poste);
            return posteDTO;
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Poste","id",id);
        }
    }

    public PosteDTO update(PosteDTO posteDTO) throws Exception {
        try {
            Poste poste = this.conversionPoste.convertToEntity(posteDTO);
            poste = this.posteRepository.save(poste);
            posteDTO = this.conversionPoste.convertToDTO(poste);
            return posteDTO;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void delete(PosteDTO posteDTO) {
        Poste poste = this.conversionPoste.convertToEntity(posteDTO);
        this.posteRepository.delete(poste);
    }

    public void deleteById(Long id) {
        this.posteRepository.deleteById(id);
    }

    public PosteDTO addTag(Long id,String tag) throws Exception {
        try {
            Poste poste = this.findById(id);
            List<String> tags = poste.getTags();
            tags.add(tag);
            poste.setTags(tags);
            poste = this.posteRepository.save(poste);
            PosteDTO posteDTO = this.conversionPoste.convertToDTO(poste);
            return posteDTO;
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Poste","id",id);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public PosteDTO mergeTags(Long id, List<String> tags) throws Exception {
        try {
            Poste poste = this.findById(id);
            List<String> tagList = poste.getTags();
            tagList.addAll(tags);
            poste.setTags(tagList);
            poste = this.posteRepository.save(poste);
            PosteDTO posteDTO = this.conversionPoste.convertToDTO(poste);
            return posteDTO;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public PosteDTO deleteTag(Long id, String tag) throws Exception {
        try {
            Poste poste = this.findById(id);
            List<String> tags = poste.getTags();
            tags.removeIf(t -> t.equalsIgnoreCase(tag));
            poste.setTags(tags);
            poste = this.posteRepository.save(poste);
            PosteDTO posteDTO = this.conversionPoste.convertToDTO(poste);
            return posteDTO;
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Poste","id",id);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
