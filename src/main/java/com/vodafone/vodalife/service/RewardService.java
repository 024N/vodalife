package com.vodafone.vodalife.service;

import com.vodafone.vodalife.model.Reward;
import com.vodafone.vodalife.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardService {

    @Autowired
    private RewardRepository rewardRepository;

    public Reward addReward(Reward reward) {
        return rewardRepository.save(reward);
    }

    public Reward updateReward(String id, Reward reward) {
        reward.setId(id);
        return rewardRepository.save(reward);
    }

    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

    public Reward getRewardById(String id) {
        return rewardRepository.findById(id).orElse(null);
    }
}