package com.vodafone.vodalife.controller;

import com.vodafone.vodalife.model.Question;
import com.vodafone.vodalife.service.QuestionService;
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
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Operation(summary = "Yeni soru ekle", description = "Yeni bir soru ekler.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Soru başarıyla eklendi.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Question.class)))
    })
    @PostMapping
    public ResponseEntity<Question> addQuestion(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Eklenecek soru bilgileri",
                required = true,
                content = @Content(mediaType = "application/json", schema = @Schema(example = "{\n" +
                        "  \"text\": \"Bu bir örnek sorudur?\",\n" +
                        "  \"options\": [\"Seçenek 1\", \"Seçenek 2\", \"Seçenek 3\", \"Seçenek 4\"],\n" +
                        "  \"correctOptionIndex\": 1\n" +
                        "}")))
            @RequestBody Question question) {
        Question createdQuestion = questionService.addQuestion(question);
        return ResponseEntity.status(201).body(createdQuestion);
    }

    @Operation(summary = "Soruyu güncelle", description = "Belirtilen ID'ye sahip soruyu günceller.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Soru başarıyla güncellendi.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Question.class))),
        @ApiResponse(responseCode = "404", description = "Belirtilen ID'ye sahip soru bulunamadı.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(
            @Parameter(description = "Soru ID'si", example = "12345")
            @PathVariable String id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Güncellenmiş soru bilgileri",
                required = true,
                content = @Content(mediaType = "application/json", schema = @Schema(example = "{\n" +
                        "  \"text\": \"Bu bir güncellenmiş sorudur?\",\n" +
                        "  \"options\": [\"Seçenek A\", \"Seçenek B\", \"Seçenek C\", \"Seçenek D\"],\n" +
                        "  \"correctOptionIndex\": 2\n" +
                        "}")))
            @RequestBody Question question) {
        Question existingQuestion = questionService.getQuestionById(id);
        if (existingQuestion == null) {
            return ResponseEntity.status(404).body(null); // 404 Not Found
        }

        Question updatedQuestion = questionService.updateQuestion(id, question);
        return ResponseEntity.ok(updatedQuestion);
    }

    @Operation(summary = "Tüm soruları getir", description = "Sistemdeki tüm soruları döner.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sorular başarıyla getirildi.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Question.class)))
    })
    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

    @Operation(summary = "ID'ye göre soru getir", description = "Belirtilen ID'ye sahip soruyu döner.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Soru başarıyla getirildi.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Question.class))),
        @ApiResponse(responseCode = "404", description = "Belirtilen ID'ye sahip soru bulunamadı.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(
            @Parameter(description = "Soru ID'si", example = "12345")
            @PathVariable String id) {
        Question question = questionService.getQuestionById(id);
        if (question == null) {
            return ResponseEntity.status(404).body(null); // 404 Not Found
        }
        return ResponseEntity.ok(question);
    }
}