package com.oc.projets.siteemploiback.service;

import com.oc.projets.siteemploiback.conversion_dto_entity.ConversionNotification;
import com.oc.projets.siteemploiback.dto.NotificationDTO;
import com.oc.projets.siteemploiback.entity.Annonce;
import com.oc.projets.siteemploiback.entity.Critere;
import com.oc.projets.siteemploiback.entity.Notification;
import com.oc.projets.siteemploiback.entity.Utilisateur;
import com.oc.projets.siteemploiback.exception.ResourceNotFoundException;
import com.oc.projets.siteemploiback.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private ConversionNotification conversionNotification;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private AnnonceService annonceService;

    @Autowired
    private CritereService critereService;

    public NotificationDTO create(NotificationDTO notificationDTO) throws Exception {
        try {
            Notification notification = this.conversionNotification.convertToEntity(notificationDTO);
            notification = this.notificationRepository.save(notification);
            notificationDTO = this.conversionNotification.convertToDTO(notification);
            return notificationDTO;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Notification findById(Long id) {
        return this.notificationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Notification","id",id));
    }

    public NotificationDTO findByIdDTO(Long id) {
        try {
            Notification notification = this.findById(id);
            NotificationDTO notificationDTO = this.conversionNotification.convertToDTO(notification);
            return notificationDTO;
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Notification","id",id);
        }
    }

    public List<Notification> getAll() {
        return this.notificationRepository.findAll();
    }

    public List<NotificationDTO> getAllDTO() {
        List<Notification> notificationList = this.getAll();
        List<NotificationDTO> notificationDTOList = notificationList.stream().map(notification -> this.conversionNotification.convertToDTO(notification)).collect(Collectors.toList());
        return notificationDTOList;
    }

    public NotificationDTO update(NotificationDTO notificationDTO) throws Exception {
        try {
            Notification notification = this.conversionNotification.convertToEntity(notificationDTO);
            notification = this.notificationRepository.save(notification);
            notificationDTO = this.conversionNotification.convertToDTO(notification);
            return notificationDTO;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void delete(NotificationDTO notificationDTO) {
        Notification notification = this.conversionNotification.convertToEntity(notificationDTO);
        this.notificationRepository.delete(notification);
    }

    public void deleteById(Long id) {
        this.notificationRepository.deleteById(id);
    }

    public NotificationDTO setReadOrNot(NotificationDTO notificationDTO) {
        notificationDTO.setRead(true);
        Notification notification = this.conversionNotification.convertToEntity(notificationDTO);
        notification = this.notificationRepository.save(notification);
        notificationDTO = this.conversionNotification.convertToDTO(notification);
        return notificationDTO;
    }

    public List<Notification> getNotificationsForOneUtilisateur(Utilisateur utilisateur) {
        Critere critere = this.critereService.getByUtilisateur(utilisateur);
        List<Annonce> annonceList = this.annonceService.findAnnonceNotReadByUtilisateur(utilisateur);
        List<Notification> notificationList = new ArrayList<>();
        if(critere == null) return notificationList;
        for(Annonce a: annonceList) {
            Notification notification = new Notification();
            notification.setUtilisateur(utilisateur);
            notification.setAnnonce(a);
            int matchLevel = critere.match(a);
            if(matchLevel > 0) {
                notification.setMatchLevel(matchLevel);
                notification.setSendMail(critere.getReceiveNotificationByMail());
                notification.setRead(false);
                notificationList.add(notification);
            }
        }
        return notificationList;
    }

    public List<Notification> sortNotificationByMatchLevel(List<Notification> notificationList) {
        return notificationList.stream().sorted(Comparator.comparing(Notification::getMatchLevel).reversed()).collect(Collectors.toList());
    }

    public List<Notification> getFirstThreeElements(List<Notification> notificationList) {
        List<Notification> notificationList1 = notificationList.size() >= 3 ? new ArrayList<>(notificationList.subList(0,3)) : notificationList;
        return notificationList1;
    }

    public List<Notification> getNotificationsForOneUtilisateurSortedAndFirstThreeElts(Utilisateur utilisateur) {
        List<Notification> notificationList = this.getNotificationsForOneUtilisateur(utilisateur);
        notificationList = sortNotificationByMatchLevel(notificationList);
        notificationList = getFirstThreeElements(notificationList);
        return notificationList;
    }

    public List<Notification> getNotificationsForAllUser() {
        List<Utilisateur> utilisateurList = this.utilisateurService.getAll();
        List<Notification> notificationList = new ArrayList<>();
        for(Utilisateur utilisateur: utilisateurList) {
            List<Notification> notificationListForOneUtilisateur = this.getNotificationsForOneUtilisateurSortedAndFirstThreeElts(utilisateur);
            notificationList.addAll(notificationListForOneUtilisateur);
        }
        return notificationList;
    }

    public List<NotificationDTO> getNotificationsForAllUserDTO() {
        List<Notification> notificationList = this.getNotificationsForAllUser();
        return notificationList.stream().map(notification -> this.conversionNotification.convertToDTO(notification)).collect(Collectors.toList());
    }

    public List<Annonce> updateAnnonceInNotificationList(List<Notification> notificationList) throws Exception {
        List<Annonce> annonceList = new ArrayList<>();
        try {
            for (Notification notification: notificationList) {
                Annonce annonce = notification.getAnnonce();
                Utilisateur utilisateur = notification.getUtilisateur();
                annonce.addUtilisateurListHowReadNotified(utilisateur);
                this.annonceService.update(annonce);
                annonceList.add(annonce);
            }
            return annonceList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public List<Notification> saveSeveralNotification(List<Notification> notificationList) throws Exception {
        List<Notification> notificationList2 = new ArrayList<>();
        try {
            for(Notification notification: notificationList) {
                notification = this.notificationRepository.save(notification);
                notificationList2.add(notification);
            }
            return notificationList2;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public List<Notification> filterBySendMailOrNot(List<Notification> notificationList) {
        List<Notification> notificationList2 = notificationList.stream().filter(notification -> notification.getSendMail()).collect(Collectors.toList());
        return notificationList2;
    }

    public List<Notification> setReadOrNotSeveralNotifications(List<Notification> notificationList) throws Exception {
        List<Notification> notificationList2 = new ArrayList<>();
        try {
            for(Notification notification: notificationList) {
                notification.setRead(true);
                notification = this.notificationRepository.save(notification);
                notificationList2.add(notification);
            }
            return notificationList2;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
