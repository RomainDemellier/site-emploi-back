package com.oc.projets.siteemploiback.conversion_dto_entity;

import com.oc.projets.siteemploiback.dto.NotificationDTO;
import com.oc.projets.siteemploiback.entity.Notification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConversionNotification {

    @Autowired
    private ModelMapper modelMapper;

    public Notification convertToEntity(NotificationDTO notificationDTO) {
        return this.modelMapper.map(notificationDTO,Notification.class);
    }

    public NotificationDTO convertToDTO(Notification notification) {
        return this.modelMapper.map(notification,NotificationDTO.class);
    }
}
