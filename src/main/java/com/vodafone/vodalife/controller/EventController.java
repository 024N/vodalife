package com.vodafone.vodalife.controller;

import com.vodafone.vodalife.model.Event;
import com.vodafone.vodalife.service.EventService;
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
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Operation(summary = "Yeni etkinlik ekle", description = "Yeni bir etkinlik ekler.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Etkinlik başarıyla eklendi.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class)))
    })
    @PostMapping
    public ResponseEntity<Event> addEvent(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Eklenecek etkinlik bilgileri",
                required = true,
                content = @Content(mediaType = "application/json", schema = @Schema(example = "{\n" +
                        "  \"title\": \"Etkinlik Başlığı\",\n" +
                        "  \"description\": \"Etkinlik açıklaması\",\n" +
                        "  \"startTime\": \"2025-04-10T10:00:00\",\n" +
                        "  \"endTime\": \"2025-04-10T12:00:00\",\n" +
                        "  \"location\": \"İstanbul, Türkiye\"\n" +
                        "}")))
            @RequestBody Event event) {
        Event createdEvent = eventService.addEvent(event);
        return ResponseEntity.status(201).body(createdEvent);
    }

    @Operation(summary = "Etkinliği güncelle", description = "Belirtilen ID'ye sahip etkinliği günceller.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Etkinlik başarıyla güncellendi.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class))),
        @ApiResponse(responseCode = "404", description = "Belirtilen ID'ye sahip etkinlik bulunamadı.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(
            @Parameter(description = "Etkinlik ID'si", example = "12345")
            @PathVariable String id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Güncellenmiş etkinlik bilgileri",
                required = true,
                content = @Content(mediaType = "application/json", schema = @Schema(example = "{\n" +
                        "  \"title\": \"Güncellenmiş Etkinlik Başlığı\",\n" +
                        "  \"description\": \"Güncellenmiş etkinlik açıklaması\",\n" +
                        "  \"startTime\": \"2025-04-10T14:00:00\",\n" +
                        "  \"endTime\": \"2025-04-10T16:00:00\",\n" +
                        "  \"location\": \"Ankara, Türkiye\"\n" +
                        "}")))
            @RequestBody Event event) {
        Event existingEvent = eventService.getEventById(id);
        if (existingEvent == null) {
            return ResponseEntity.status(404).body(null); // 404 Not Found
        }

        Event updatedEvent = eventService.updateEvent(id, event);
        return ResponseEntity.ok(updatedEvent);
    }

    @Operation(summary = "Tüm etkinlikleri getir", description = "Sistemdeki tüm etkinlikleri döner.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Etkinlikler başarıyla getirildi.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class)))
    })
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @Operation(summary = "ID'ye göre etkinlik getir", description = "Belirtilen ID'ye sahip etkinliği döner.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Etkinlik başarıyla getirildi.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Event.class))),
        @ApiResponse(responseCode = "404", description = "Belirtilen ID'ye sahip etkinlik bulunamadı.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(
            @Parameter(description = "Etkinlik ID'si", example = "12345")
            @PathVariable String id) {
        Event event = eventService.getEventById(id);
        if (event == null) {
            return ResponseEntity.status(404).body(null); // 404 Not Found
        }
        return ResponseEntity.ok(event);
    }
}