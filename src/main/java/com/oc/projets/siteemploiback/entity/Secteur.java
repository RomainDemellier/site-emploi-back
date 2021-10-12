package com.oc.projets.siteemploiback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "secteurs")
public class Secteur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nom;


    @JsonIgnoreProperties("secteur")
    @OneToMany(mappedBy = "secteur", targetEntity = Poste.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Poste> posteList = new ArrayList<Poste>();

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

    public List<Poste> getPosteList() {
        return posteList;
    }

    public void setPosteList(List<Poste> posteList) {
        this.posteList = posteList;
    }

    public void addPoste(Poste poste) {
        this.posteList.add(poste);
    }

    @Override
    public String toString() {
        return "Secteur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
//                ", posteList=" + posteList +
                '}';
    }
}
