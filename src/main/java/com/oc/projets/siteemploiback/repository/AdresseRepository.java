package com.oc.projets.siteemploiback.repository;

import com.oc.projets.siteemploiback.entity.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse,Long> {
}
