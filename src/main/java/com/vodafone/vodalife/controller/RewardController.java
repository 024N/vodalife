package com.vodafone.vodalife.controller;

import com.vodafone.vodalife.model.Reward;
import com.vodafone.vodalife.service.RewardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @Operation(summary = "Yeni ödül ekle", description = "Yeni bir ödül ekler.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Ödül başarıyla eklendi.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reward.class)))
    })
    @PostMapping
    public ResponseEntity<Reward> addReward(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Eklenecek ödül bilgileri",
                required = true,
                content = @Content(mediaType = "application/json", schema = @Schema(example = "{\n" +
                        "  \"title\": \"Ödül Başlığı\",\n" +
                        "  \"description\": \"Ödül açıklaması\",\n" +
                        "  \"points\": 100,\n" +
                        "  \"progress\": 50,\n" +
                        "  \"image\": \"https://example.com/image.png\",\n" +
                        "  \"type\": \"achievement\",\n" +
                        "  \"expiryDate\": \"2025-12-31\"\n" +
                        "}")))
            @RequestBody Reward reward) {
        Reward createdReward = rewardService.addReward(reward);
        return ResponseEntity.status(201).body(createdReward);
    }

    @Operation(summary = "Ödülü güncelle", description = "Belirtilen ID'ye sahip ödülü günceller.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ödül başarıyla güncellendi.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reward.class))),
        @ApiResponse(responseCode = "404", description = "Belirtilen ID'ye sahip ödül bulunamadı.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Reward> updateReward(
            @Parameter(description = "Ödül ID'si", example = "12345")
            @PathVariable String id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Güncellenmiş ödül bilgileri",
                required = true,
                content = @Content(mediaType = "application/json", schema = @Schema(example = "{\n" +
                        "  \"title\": \"Güncellenmiş Ödül Başlığı\",\n" +
                        "  \"description\": \"Güncellenmiş ödül açıklaması\",\n" +
                        "  \"points\": 200,\n" +
                        "  \"progress\": 100,\n" +
                        "  \"image\": \"https://example.com/new-image.png\",\n" +
                        "  \"type\": \"voucher\",\n" +
                        "  \"expiryDate\": \"2026-01-01\"\n" +
                        "}")))
            @RequestBody Reward reward) {
        Reward existingReward = rewardService.getRewardById(id);
        if (existingReward == null) {
            return ResponseEntity.status(404).body(null); // 404 Not Found
        }

        Reward updatedReward = rewardService.updateReward(id, reward);
        return ResponseEntity.ok(updatedReward);
    }

    @Operation(summary = "Tüm ödülleri getir", description = "Sistemdeki tüm ödülleri döner.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ödüller başarıyla getirildi.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reward.class)))
    })
    @GetMapping
    public ResponseEntity<List<Reward>> getAllRewards() {
        List<Reward> rewards = rewardService.getAllRewards();
        return ResponseEntity.ok(rewards);
    }

    @Operation(summary = "ID'ye göre ödül getir", description = "Belirtilen ID'ye sahip ödülü döner.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ödül başarıyla getirildi.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reward.class))),
        @ApiResponse(responseCode = "404", description = "Belirtilen ID'ye sahip ödül bulunamadı.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Reward> getRewardById(
            @Parameter(description = "Ödül ID'si", example = "12345")
            @PathVariable String id) {
        Reward reward = rewardService.getRewardById(id);
        if (reward == null) {
            return ResponseEntity.status(404).body(null); // 404 Not Found
        }
        return ResponseEntity.ok(reward);
    }
}