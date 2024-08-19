package com.example.erp.url.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("UrlMapping")
public class UrlShortener {
    @Id
    private String id;

    private String url;
    private String hashCode;

    private LocalDateTime createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "UrlShortener{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", hashCode='" + hashCode + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
