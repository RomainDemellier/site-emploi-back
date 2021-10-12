package com.oc.projets.siteemploiback.repository;

import com.oc.projets.siteemploiback.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
    Utilisateur findByIdAndRole(Long id, String role);
    List<Utilisateur> findAllByRole(String role);
    Utilisateur findByEmail(String email);
}
