package com.vodafone.vodalife.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "events") // MongoDB'de "events" koleksiyonunda saklanacak
public class Event {

    @Id
    private String id; // Etkinlik ID'si
    private String title; // Etkinlik başlığı
    private String description; // Etkinlik açıklaması
    private LocalDateTime startTime; // Etkinlik başlangıç zamanı
    private LocalDateTime endTime; // Etkinlik bitiş zamanı
    private String location; // Etkinlik yeri

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}