package com.oc.projets.siteemploiback.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @NotNull
    private Utilisateur utilisateur;

    @OneToOne
    @NotNull
    private Annonce annonce;

    @NotNull
    private int matchLevel;

    private Boolean sendMail;

    private Boolean read;

    public Notification() {
    }

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

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    public int getMatchLevel() {
        return matchLevel;
    }

    public void setMatchLevel(int matchLevel) {
        this.matchLevel = matchLevel;
    }

    public Boolean getSendMail() {
        return sendMail;
    }

    public void setSendMail(Boolean sendMailOrNot) {
        this.sendMail = sendMailOrNot;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean readOrNot) {
        this.read = readOrNot;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", utilisateur=" + utilisateur +
                ", annonce=" + annonce +
                ", matchLevel=" + matchLevel +
                ", sendMailOrNot=" + sendMail +
                ", readOrNot=" + read +
                '}';
    }
}
