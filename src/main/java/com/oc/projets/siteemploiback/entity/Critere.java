package com.oc.projets.siteemploiback.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "criteres")
public class Critere implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
//    @PrimaryKeyJoinColumn
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "secteur_id", nullable = false)
    @JsonIgnoreProperties("posteList")
//    @NotNull
    private Secteur secteur;

    @ManyToOne
    @JoinColumn(name = "poste_id", nullable = false)
    @JsonIgnoreProperties("secteur")
//    @NotNull
    private Poste poste;

    @NotNull
    private int salaireMinimum;

    @NotNull
    private float maxDistanceFromHome;

    private Boolean receiveNotificationByMail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Secteur getSecteur() {
        return secteur;
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
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

    @Override
    public String toString() {
        return "Critere{" +
                "id=" + id +
                ", utilisateur=" + utilisateur +
                ", secteur=" + secteur +
                ", poste=" + poste +
                ", salaireMinimum=" + salaireMinimum +
                ", maxDistanceFromHome=" + maxDistanceFromHome +
                ", receiveNotificationByMail=" + receiveNotificationByMail +
                '}';
    }

    public int match(Annonce annonce) {
        int matchLevel = 0;
        Poste posteAnnonce = annonce.getPoste();
        List<String> tagListPoste = this.poste.getTags();
        Secteur secteurAnnonce = annonce.getSecteur();
        int salaireMinimumAnnonce = annonce.getSalaireMinimum();
        String titreAnnonce = annonce.getTitre();
        String descriptionAnnonce = annonce.getDescription();
        if(this.poste.equals(posteAnnonce) && this.salaireMinimum <= salaireMinimumAnnonce) {
            return 20;
        }
        if(this.poste.equals(posteAnnonce)) {
            matchLevel += 5;
        }
        if(this.salaireMinimum <= salaireMinimumAnnonce) {
            matchLevel += 5;
        }
        if(this.secteur.equals(secteurAnnonce)) {
            matchLevel += 4;
        }
        if(tagListPoste.stream().anyMatch(tag -> titreAnnonce.toLowerCase().contains(tag))) {
            matchLevel += 4;
        }
        if(tagListPoste.stream().anyMatch(tag -> descriptionAnnonce.toLowerCase().contains(tag))) {
            matchLevel += 4;
        }
        return matchLevel;
    }
}
