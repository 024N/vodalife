package com.vodafone.vodalife.repository;

import com.vodafone.vodalife.model.Reward;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardRepository extends MongoRepository<Reward, String> {
}