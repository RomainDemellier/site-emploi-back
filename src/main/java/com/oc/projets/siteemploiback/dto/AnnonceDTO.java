package com.oc.projets.siteemploiback.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.oc.projets.siteemploiback.enumeration.TypeContrat;

import java.util.ArrayList;
import java.util.List;

public class AnnonceDTO {

    private Long id;

    private String titre;

    private UtilisateurDTO recruteur;

    @JsonIgnoreProperties("posteList")
    private SecteurDTO secteur;

    @JsonIgnoreProperties("secteur")
    private PosteDTO poste;

    private List<UtilisateurDTO> utilisateurListWhoReadNotified = new ArrayList<UtilisateurDTO>();

    private TypeContrat typeContrat;

    private String description;

    private int salaireMinimum;

    private int salaireMaximum;

    private Boolean publie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public UtilisateurDTO getRecruteur() {
        return recruteur;
    }

    public void setRecruteur(UtilisateurDTO recruteur) {
        this.recruteur = recruteur;
    }

    public SecteurDTO getSecteur() {
        return secteur;
    }

    public void setSecteur(SecteurDTO secteur) {
        this.secteur = secteur;
    }

    public PosteDTO getPoste() {
        return poste;
    }

    public void setPoste(PosteDTO poste) {
        this.poste = poste;
    }

    public List<UtilisateurDTO> getUtilisateurListWhoReadNotified() {
        return utilisateurListWhoReadNotified;
    }

    public void setUtilisateurListWhoReadNotified(List<UtilisateurDTO> utilisateurListWhoReadNotified) {
        this.utilisateurListWhoReadNotified = utilisateurListWhoReadNotified;
    }

    public void addUtilisateurListHowRead(UtilisateurDTO utilisateur) {
        this.utilisateurListWhoReadNotified.add(utilisateur);
    }

/*    public void addAllUtilisateurListHowRead(List<UtilisateurDTO> utilisateurList) {
        this.utilisateurListWhoReadNotified.addAll(utilisateurList);
    }*/

    public TypeContrat getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(TypeContrat typeContrat) {
        this.typeContrat = typeContrat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSalaireMinimum() {
        return salaireMinimum;
    }

    public void setSalaireMinimum(int salaireMinimum) {
        this.salaireMinimum = salaireMinimum;
    }

    public int getSalaireMaximum() {
        return salaireMaximum;
    }

    public void setSalaireMaximum(int salaireMaximum) {
        this.salaireMaximum = salaireMaximum;
    }

    public Boolean getPublie() {
        return publie;
    }

    public void setPublie(Boolean publie) {
        this.publie = publie;
    }
}
