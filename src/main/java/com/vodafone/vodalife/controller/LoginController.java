package com.vodafone.vodalife.controller;

import com.vodafone.vodalife.model.Customer;
import com.vodafone.vodalife.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Müşteri giriş yap", description = "Telefon numarası ve şifre ile müşteri giriş yapar.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Giriş başarılı"),
        @ApiResponse(responseCode = "404", description = "Böyle bir müşteri yok"),
        @ApiResponse(responseCode = "401", description = "Şifre hatalı")
    })
    @PostMapping
    public ResponseEntity<String> login(
            @Parameter(description = "Müşteri telefon numarası", example = "5551234567")
            @RequestParam String phoneNumber,
            @Parameter(description = "Müşteri şifresi", example = "password123")
            @RequestParam String password) {
        logger.info("Müşteri giriş yapıyor. Telefon Numarası: {}", phoneNumber);

        Optional<Customer> customerOptional = customerService.getCustomerByPhoneNumber(phoneNumber);

        if (customerOptional.isEmpty()) {
            logger.warn("Telefon numarası bulunamadı: {}", phoneNumber);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Böyle bir müşteri yok.");
        }

        Customer customer = customerOptional.get();
        if (!customer.getPassword().equals(password)) {
            logger.warn("Şifre hatalı. Telefon Numarası: {}", phoneNumber);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Şifre hatalı.");
        }

        logger.info("Müşteri giriş başarılı. Telefon Numarası: {}", phoneNumber);
        return ResponseEntity.ok("Giriş başarılı.");
    }
}