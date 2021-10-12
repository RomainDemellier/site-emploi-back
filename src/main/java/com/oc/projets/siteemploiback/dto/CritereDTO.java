package com.oc.projets.siteemploiback.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CritereDTO {

    private Long id;

    private UtilisateurDTO utilisateur;

    @JsonIgnoreProperties("posteList")
    private SecteurDTO secteur;

    @JsonIgnoreProperties("secteur")
    private PosteDTO poste;

    private int salaireMinimum;

    private float maxDistanceFromHome;

    private Boolean receiveNotificationByMail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UtilisateurDTO getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UtilisateurDTO utilisateur) {
        this.utilisateur = utilisateur;
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

    public int getSalaireMinimum() {
        return salaireMinimum;
    }

    public void setSalaireMinimum(int salaireMinimum) {
        this.salaireMinimum = salaireMinimum;
    }

    public float getMaxDistanceFromHome() {
        return maxDistanceFromHome;
    }

    public void setMaxDistanceFromHome(float maxDistanceFromHome) {
        this.maxDistanceFromHome = maxDistanceFromHome;
    }

    public Boolean getReceiveNotificationByMail() {
        return receiveNotificationByMail;
    }

    public void setReceiveNotificationByMail(Boolean receiveNotificationByMail) {
        this.receiveNotificationByMail = receiveNotificationByMail;
    }
}
