package com.vodafone.vodalife.controller;

import com.vodafone.vodalife.model.RecyclingPoint;
import com.vodafone.vodalife.service.RecyclingPointService;
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
@RequestMapping("/api/recycling-points")
public class RecyclingPointController {

    @Autowired
    private RecyclingPointService recyclingPointService;

    @Operation(summary = "Yeni geri dönüşüm noktası oluştur", description = "Yeni bir geri dönüşüm noktası ekler.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Geri dönüşüm noktası başarıyla oluşturuldu.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RecyclingPoint.class)))
    })
    @PostMapping
    public ResponseEntity<RecyclingPoint> createRecyclingPoint(
            @RequestBody RecyclingPoint recyclingPoint) {
        RecyclingPoint createdPoint = recyclingPointService.createRecyclingPoint(recyclingPoint);
        return ResponseEntity.status(201).body(createdPoint);
    }

    @Operation(summary = "Geri dönüşüm noktasını güncelle", description = "Belirtilen ID'ye sahip geri dönüşüm noktasını günceller.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Geri dönüşüm noktası başarıyla güncellendi.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RecyclingPoint.class))),
        @ApiResponse(responseCode = "404", description = "Belirtilen ID'ye sahip geri dönüşüm noktası bulunamadı.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<RecyclingPoint> updateRecyclingPoint(
            @Parameter(description = "Geri dönüşüm noktası ID'si", example = "12345")
            @PathVariable String id,
            @RequestBody RecyclingPoint recyclingPoint) {
        RecyclingPoint existingPoint = recyclingPointService.getRecyclingPointById(id);
        if (existingPoint == null) {
            return ResponseEntity.status(404).body(null); // 404 Not Found
        }

        RecyclingPoint updatedPoint = recyclingPointService.updateRecyclingPoint(id, recyclingPoint);
        return ResponseEntity.ok(updatedPoint);
    }

    @Operation(summary = "Tüm geri dönüşüm noktalarını getir", description = "Sistemdeki tüm geri dönüşüm noktalarını döner.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Geri dönüşüm noktaları başarıyla getirildi.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RecyclingPoint.class)))
    })
    @GetMapping
    public ResponseEntity<List<RecyclingPoint>> getAllRecyclingPoints() {
        List<RecyclingPoint> points = recyclingPointService.getAllRecyclingPoints();
        return ResponseEntity.ok(points);
    }

    @Operation(summary = "Yakındaki geri dönüşüm noktalarını getir", description = "Verilen konumun belirli bir mesafe içindeki geri dönüşüm noktalarını döner.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Yakındaki geri dönüşüm noktaları başarıyla getirildi.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RecyclingPoint.class)))
    })
    @GetMapping("/nearby")
    public ResponseEntity<List<RecyclingPoint>> getNearbyRecyclingPoints(
            @Parameter(description = "Latitude (enlem)", example = "41.015137")
            @RequestParam double latitude,
            @Parameter(description = "Longitude (boylam)", example = "28.979530")
            @RequestParam double longitude,
            @Parameter(description = "Mesafe (km cinsinden)", example = "10")
            @RequestParam double radiusInKm) {
        List<RecyclingPoint> points = recyclingPointService.getNearbyRecyclingPoints(latitude, longitude, radiusInKm);
        return ResponseEntity.ok(points);
    }
}