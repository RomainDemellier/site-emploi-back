package com.oc.projets.siteemploiback.dto;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurDTO {

    private Long id;

    private String nom;

    private String prenom;

    private String email;

    private String password;

    private AdresseDTO adresse;

    private List<AdresseDTO> adressesSecondaires = new ArrayList<AdresseDTO>();

    private String role;

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AdresseDTO getAdresse() {
        return adresse;
    }

    public void setAdresse(AdresseDTO adresse) {
        this.adresse = adresse;
    }

    public List<AdresseDTO> getAdressesSecondaires() {
        return adressesSecondaires;
    }

    public void setAdressesSecondaires(List<AdresseDTO> adressesSecondaires) {
        this.adressesSecondaires = adressesSecondaires;
    }

    public void addAdresseSecondaires(AdresseDTO adresseDTO) {
        this.adressesSecondaires.add(adresseDTO);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
