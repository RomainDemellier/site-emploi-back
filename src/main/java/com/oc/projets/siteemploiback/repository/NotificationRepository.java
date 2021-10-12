package com.oc.projets.siteemploiback.repository;

import com.oc.projets.siteemploiback.entity.Notification;
import com.oc.projets.siteemploiback.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {

    List<Notification> findByUtilisateurAndAndRead(Utilisateur utilisateur, Boolean readOrNot);
}
