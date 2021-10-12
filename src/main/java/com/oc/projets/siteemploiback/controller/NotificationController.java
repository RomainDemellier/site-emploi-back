package com.oc.projets.siteemploiback.controller;

import com.oc.projets.siteemploiback.dto.NotificationDTO;
import com.oc.projets.siteemploiback.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/allUsers")
    public ResponseEntity getNotificationsForUsers() {
        try {
            List<NotificationDTO> notificationDTOList = this.notificationService.getNotificationsForAllUserDTO();
            return ResponseEntity.ok(notificationDTOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
