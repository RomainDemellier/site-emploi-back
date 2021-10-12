package com.oc.projets.siteemploiback.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "adresses")
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Veuillez saisir un nom de rue.")
    private String nomRue;

    @NotBlank(message = "Veuillez saisir un numéro de rue.")
    private String numero;

    @NotBlank(message = "Veuillez choisir une ville.")
    private String ville;

    @NotBlank(message = "Veuillez saisir un code postal.")
    @Column(name = "code_postal")
    private String codePostal;

    @NotBlank(message = "Veuillez sélectionner un code région.")
    @Column(name = "code_region")
    private String codeRegion;

    @NotBlank(message = "Veuillez sélectionner un code département.")
    @Column(name = "code_departement")
    private String codeDepartement;

    @NotBlank(message = "Veuillez sélectionner un pays.")
    private String pays;

    private String nom;

    public Adresse() {
    }

    public Adresse(@NotBlank(message = "Veuillez saisir un nom de rue.") String nomRue, @NotBlank(message = "Veuillez saisir un numéro de rue.") String numero, @NotBlank(message = "Veuillez choisir une ville.") String ville, @NotBlank(message = "Veuillez saisir un code postal.") String codePostal, @NotBlank(message = "Veuillez sélectionner un code région.") String codeRegion, @NotBlank(message = "Veuillez sélectionner un code département.") String codeDepartement, @NotBlank(message = "Veuillez sélectionner un pays.") String pays) {
        this.nomRue = nomRue;
        this.numero = numero;
        this.ville = ville;
        this.codePostal = codePostal;
        this.codeRegion = codeRegion;
        this.codeDepartement = codeDepartement;
        this.pays = pays;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomRue() {
        return nomRue;
    }

    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getCodeRegion() {
        return codeRegion;
    }

    public void setCodeRegion(String codeRegion) {
        this.codeRegion = codeRegion;
    }

    public String getCodeDepartement() {
        return codeDepartement;
    }

    public void setCodeDepartement(String codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "id=" + id +
                ", nomRue='" + nomRue + '\'' +
                ", numero='" + numero + '\'' +
                ", ville='" + ville + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", codeRegion='" + codeRegion + '\'' +
                ", codeDepartement='" + codeDepartement + '\'' +
                ", pays='" + pays + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}
