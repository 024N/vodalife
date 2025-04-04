package com.vodafone.vodalife.repository;

import com.vodafone.vodalife.model.BillTracker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillTrackerRepository extends MongoRepository<BillTracker, String> {
    Optional<BillTracker> findByUserId(String userId);
}