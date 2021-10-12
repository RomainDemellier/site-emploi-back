package com.oc.projets.siteemploiback.repository;

import com.oc.projets.siteemploiback.entity.Annonce;
import com.oc.projets.siteemploiback.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce,Long> {

    List<Annonce> findByUtilisateurListWhoReadNotifiedNotContains(Utilisateur utilisateur);
}
