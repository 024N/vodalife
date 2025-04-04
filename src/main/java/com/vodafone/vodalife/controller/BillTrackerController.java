package com.vodafone.vodalife.controller;

import com.vodafone.vodalife.model.BillTracker;
import com.vodafone.vodalife.service.BillTrackerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bills")
public class BillTrackerController {

    @Autowired
    private BillTrackerService billTrackerService;

    @Operation(summary = "Fatura ekle", description = "Belirtilen kullanıcı için su veya elektrik faturası ekler.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Fatura başarıyla eklendi."),
        @ApiResponse(responseCode = "404", description = "Kullanıcı bulunamadı.")
    })
    @PostMapping("/{userId}")
    public ResponseEntity<BillTracker> addBill(
            @Parameter(description = "Kullanıcı ID'si", example = "user123")
            @PathVariable String userId,
            @Parameter(description = "Fatura türü (örneğin: water, electricity)", example = "water")
            @RequestParam String type,
            @Parameter(description = "Tüketim miktarı", example = "150")
            @RequestParam double value,
            @Parameter(description = "Tüketim birimi (örneğin: kWh, m3)", example = "m3")
            @RequestParam String unit,
            @Parameter(description = "Fatura maliyeti", example = "75.7")
            @RequestParam double cost,
            @Parameter(description = "Fatura ayı (YYYY-MM formatında)", example = "2025-04")
            @RequestParam String month) {
        BillTracker updatedTracker = billTrackerService.addBill(userId, type, value, unit, cost, month);
        return ResponseEntity.ok(updatedTracker);
    }

    @Operation(summary = "Kullanıcının fatura bilgilerini getir", description = "Belirtilen kullanıcı için tüm fatura kayıtlarını döner.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Fatura bilgileri başarıyla getirildi."),
        @ApiResponse(responseCode = "404", description = "Kullanıcı bulunamadı.")
    })
    @GetMapping("/{userId}")
    public ResponseEntity<BillTracker> getBillsByUserId(
            @Parameter(description = "Kullanıcı ID'si", example = "user123")
            @PathVariable String userId) {
        BillTracker billTracker = billTrackerService.getBillsByUserId(userId);
        return ResponseEntity.ok(billTracker);
    }
}