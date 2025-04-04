package com.vodafone.vodalife.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "step_tracker") // MongoDB'de "step_tracker" koleksiyonunda saklanacak
public class StepTracker {

    @Id
    private String id; // MongoDB'nin otomatik oluşturduğu ID
    private String userId; // Kullanıcı ID'si (Customer ile ilişkilendirme)
    private List<StepRecord> stepRecords = new ArrayList<>(); // Günlük adım kayıtları

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<StepRecord> getStepRecords() {
        return stepRecords;
    }

    public void setStepRecords(List<StepRecord> stepRecords) {
        this.stepRecords = stepRecords;
    }
}