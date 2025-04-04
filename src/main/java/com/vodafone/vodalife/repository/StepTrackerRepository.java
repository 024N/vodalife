package com.vodafone.vodalife.repository;

import com.vodafone.vodalife.model.StepTracker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StepTrackerRepository extends MongoRepository<StepTracker, String> {
    // Kullanıcı ID'sine göre StepTracker kaydını bul
    Optional<StepTracker> findByUserId(String userId);
}