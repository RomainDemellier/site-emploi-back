package com.oc.projets.siteemploiback.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "utilisateurs")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Veuillez saisir un nom.")
    private String nom;

//    @NotBlank(message = "Veuillez saisir un prénom.")
    private String prenom;

    @NotBlank(message = "Veuillez saisir un email.")
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Veuillez saisir un mot de passe.")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_id", referencedColumnName = "id")
    private Adresse adresse;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Adresse> adressesSecondaires = new ArrayList<Adresse>();

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

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public List<Adresse> getAdressesSecondaires() {
        return adressesSecondaires;
    }

    public void setAdressesSecondaires(List<Adresse> adressesSecondaires) {
        this.adressesSecondaires = adressesSecondaires;
    }

    public void addAdresseSecondaire(Adresse adresse) {
        this.adressesSecondaires.add(adresse);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", adresse=" + adresse +
                ", adressesSecondaires=" + adressesSecondaires +
                ", role='" + role + '\'' +
                '}';
    }
}
