package com.oc.projets.siteemploiback.dto;

public class NotificationDTO {

    private Long id;

    private UtilisateurDTO utilisateur;

    private AnnonceDTO annonce;

    private int matchLevel;

    private Boolean sendMail;

    private Boolean read;

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

    public AnnonceDTO getAnnonce() {
        return annonce;
    }

    public void setAnnonce(AnnonceDTO annonce) {
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

    public void setSendMail(Boolean sendMail) {
        this.sendMail = sendMail;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }
}
