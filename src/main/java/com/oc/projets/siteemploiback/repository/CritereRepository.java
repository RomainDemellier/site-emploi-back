package com.oc.projets.siteemploiback.repository;

import com.oc.projets.siteemploiback.entity.Critere;
import com.oc.projets.siteemploiback.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CritereRepository extends JpaRepository<Critere,Long> {
    public Critere findByUtilisateur(Utilisateur utilisateur);
}
