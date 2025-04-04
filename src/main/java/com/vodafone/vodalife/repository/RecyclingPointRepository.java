package com.vodafone.vodalife.repository;

import com.vodafone.vodalife.model.RecyclingPoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecyclingPointRepository extends MongoRepository<RecyclingPoint, String> {
}