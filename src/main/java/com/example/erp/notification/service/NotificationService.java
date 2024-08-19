package com.example.erp.notification.service;

import com.example.erp.notification.domain.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class NotificationService {
    @Autowired
    Environment env;

    private String discordWebhookUrl() {
        return env.getProperty("discord_webhook");
    }

    private String loggedInUser() {
        return "Priyank Akbari";
    }

    public void sendDiscordNotification(Notification notification) throws Exception {
        Map<String, Object> requestBody = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        List<Map<String, String>> fields = new ArrayList<>();
        Map<String, String> field = new HashMap<>();
        List<Map<String, Object>> embeds = new ArrayList<>();
        Map<String, Object> embed = new HashMap<>();

        embed.put("title", notification.getTitle());
        embed.put("description", notification.getDescription());
        field.put("name", "LoggedInUser");
        field.put("value", loggedInUser());
        fields.add(field);
        embed.put("type", "rich");
        embed.put("fields", fields);
        embeds.add(embed);
        requestBody.put("embeds", embeds);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(discordWebhookUrl(), requestEntity, String.class);
    }
}
