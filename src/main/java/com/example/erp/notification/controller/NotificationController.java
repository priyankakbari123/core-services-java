package com.example.erp.notification.controller;

import com.example.erp.notification.domain.Notification;
import com.example.erp.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @PostMapping(value = "/discord/add",produces = "application/json")
    public void sendDiscordNotification(@RequestBody Notification notification)throws Exception{
        try{
            notificationService.sendDiscordNotification(notification);
        }catch (Exception e){
            throw e;
        }
    }
}
