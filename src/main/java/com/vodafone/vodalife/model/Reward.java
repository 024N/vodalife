package com.vodafone.vodalife.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "rewards") // MongoDB'de "rewards" koleksiyonunda saklanacak
public class Reward {

    @Id
    private String id; // Ödül ID'si
    private String title; // Ödül başlığı
    private String description; // Ödül açıklaması
    private int points; // Ödül için gereken puan
    private int progress; // Kullanıcının ödüldeki ilerlemesi
    private String image; // Ödül resmi URL'si
    private String type; // Ödül tipi: 'achievement', 'voucher', 'discount'
    private LocalDate expiryDate; // Ödülün son kullanma tarihi (opsiyonel)

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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}