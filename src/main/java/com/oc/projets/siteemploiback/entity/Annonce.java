package com.oc.projets.siteemploiback.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.oc.projets.siteemploiback.enumeration.TypeContrat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "annonces")
public class Annonce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Veuillez saisir un titre.")
    private String titre;

//    @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "recruteur_id", nullable = false)
    private Utilisateur recruteur;

//    @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "secteur_id", nullable = false)
    @JsonIgnoreProperties("posteList")
    private Secteur secteur;

//    @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "poste_id", nullable = false)
    @JsonIgnoreProperties("secteur")
    private Poste poste;

    @ManyToMany
    private List<Utilisateur> utilisateurListWhoReadNotified = new ArrayList<Utilisateur>();

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

    public Utilisateur getRecruteur() {
        return recruteur;
    }

    public void setRecruteur(Utilisateur recruteur) {
        this.recruteur = recruteur;
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

    public List<Utilisateur> getUtilisateurListWhoReadNotified() {
        return utilisateurListWhoReadNotified;
    }

    public void setUtilisateurListWhoReadNotified(List<Utilisateur> utilisateurListHowRead) {
        this.utilisateurListWhoReadNotified = utilisateurListHowRead;
    }

    public void addUtilisateurListHowReadNotified(Utilisateur utilisateur) {
        this.utilisateurListWhoReadNotified.add(utilisateur);
    }

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

    @Override
    public String toString() {
        return "Annonce{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", recruteur=" + recruteur +
                ", secteur=" + secteur +
                ", poste=" + poste +
                ", typeContrat=" + typeContrat +
                ", description='" + description + '\'' +
                ", salaireMinimum=" + salaireMinimum +
                ", salaireMaximum=" + salaireMaximum +
                ", publie=" + publie +
                '}';
    }
}
