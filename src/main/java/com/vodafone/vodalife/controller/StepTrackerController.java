package com.vodafone.vodalife.controller;

import com.vodafone.vodalife.model.StepTracker;
import com.vodafone.vodalife.service.StepTrackerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/steps")
public class StepTrackerController {

    @Autowired
    private StepTrackerService stepTrackerService;

    @Operation(summary = "Adım sayısını güncelle", description = "Belirtilen kullanıcı için adım sayısını günceller.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Adım sayısı başarıyla güncellendi."),
        @ApiResponse(responseCode = "404", description = "Kullanıcı bulunamadı.")
    })
    @PostMapping("/{userId}")
    public ResponseEntity<StepTracker> updateSteps(
            @Parameter(description = "Kullanıcı ID'si", example = "user123")
            @PathVariable String userId,
            @Parameter(description = "Adım sayısı", example = "5000")
            @RequestParam int steps,
            @Parameter(description = "Tarih (YYYY-MM-DD formatında, opsiyonel)", example = "2025-04-01")
            @RequestParam(required = false) String date) {
        LocalDate parsedDate = (date != null) ? LocalDate.parse(date) : LocalDate.now();
        StepTracker updatedTracker = stepTrackerService.updateSteps(userId, steps, parsedDate);
        return ResponseEntity.ok(updatedTracker);
    }

    @Operation(summary = "Kullanıcının adım bilgilerini getir", description = "Belirtilen kullanıcı için tüm adım kayıtlarını döner.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Adım bilgileri başarıyla getirildi."),
        @ApiResponse(responseCode = "404", description = "Kullanıcı bulunamadı.")
    })
    @GetMapping("/{userId}")
    public ResponseEntity<StepTracker> getStepTrackerByUserId(
            @Parameter(description = "Kullanıcı ID'si", example = "user123")
            @PathVariable String userId) {
        StepTracker stepTracker = stepTrackerService.getStepTrackerByUserId(userId);
        return ResponseEntity.ok(stepTracker);
    }
}