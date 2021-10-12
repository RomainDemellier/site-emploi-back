package com.oc.projets.siteemploiback.repository;

import com.oc.projets.siteemploiback.entity.Secteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecteurRepository extends JpaRepository<Secteur,Long> {
}
