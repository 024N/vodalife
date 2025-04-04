package com.vodafone.vodalife.service;

import com.vodafone.vodalife.model.StepRecord;
import com.vodafone.vodalife.model.StepTracker;
import com.vodafone.vodalife.repository.StepTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class StepTrackerService {

    @Autowired
    private StepTrackerRepository stepTrackerRepository;

    public StepTracker updateSteps(String userId, int steps, LocalDate date) {
        StepTracker stepTracker = stepTrackerRepository.findByUserId(userId)
                .orElseGet(() -> {
                    StepTracker newTracker = new StepTracker();
                    newTracker.setUserId(userId);
                    return newTracker;
                });

        // Belirtilen tarihteki adım kaydını kontrol et
        Optional<StepRecord> recordForDate = stepTracker.getStepRecords().stream()
                .filter(record -> record.getDate().equals(date))
                .findFirst();

        if (recordForDate.isPresent()) {
            // Belirtilen tarihteki adımları güncelle
            StepRecord record = recordForDate.get();
            record.setSteps(steps); // Toplama yapma, doğrudan verilen adım sayısını güncelle
        } else {
            // Yeni bir günlük kayıt ekle
            stepTracker.getStepRecords().add(new StepRecord(date, steps));
        }

        // Güncellenmiş bilgileri kaydet
        return stepTrackerRepository.save(stepTracker);
    }

    public StepTracker getStepTrackerByUserId(String userId) {
        return stepTrackerRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı."));
    }
}