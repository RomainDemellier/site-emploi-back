package com.oc.projets.siteemploiback.repository;

import com.oc.projets.siteemploiback.entity.Poste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosteRepository extends JpaRepository<Poste,Long> {
}
