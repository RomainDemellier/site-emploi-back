package com.oc.projets.siteemploiback.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

public class PosteDTO implements Serializable {

    private Long id;

    private String intitule;

    private List<String> tags;

    @JsonIgnoreProperties("posteList")
    private SecteurDTO secteur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public SecteurDTO getSecteur() {
        return secteur;
    }

    public void setSecteur(SecteurDTO secteur) {
        this.secteur = secteur;
    }
}
