package com.oc.projets.siteemploiback.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SecteurDTO implements Serializable {

    private Long id;

    private String nom;

    @JsonIgnoreProperties("secteur")
    private List<PosteDTO> posteList = new ArrayList<PosteDTO>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<PosteDTO> getPosteList() {
        return posteList;
    }

    public void setPosteList(List<PosteDTO> posteList) {
        this.posteList = posteList;
    }

    public void addPoste(PosteDTO posteDTO) {
        this.posteList.add(posteDTO);
    }
}
