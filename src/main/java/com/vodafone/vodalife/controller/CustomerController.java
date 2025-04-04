package com.vodafone.vodalife.controller;

import com.vodafone.vodalife.model.Customer;
import com.vodafone.vodalife.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Yeni müşteri oluştur", description = "Yeni bir müşteri kaydı oluşturur.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Müşteri başarıyla oluşturuldu."),
        @ApiResponse(responseCode = "400", description = "Geçersiz müşteri verisi.")
    })
    @PostMapping
    public ResponseEntity<Customer> createCustomer(
            @Parameter(description = "Müşteri bilgileri", required = true)
            @RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @Operation(summary = "Tüm müşterileri getir", description = "Sistemdeki tüm müşterileri döner.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Müşteriler başarıyla getirildi.")
    })
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @Operation(summary = "ID'ye göre müşteri getir", description = "Belirtilen ID'ye sahip müşteriyi döner.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Müşteri başarıyla getirildi."),
        @ApiResponse(responseCode = "404", description = "Müşteri bulunamadı.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Customer>> getCustomerById(
            @Parameter(description = "Müşteri ID'si", example = "customer123")
            @PathVariable String id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @Operation(summary = "Müşteri güncelle", description = "Belirtilen ID'ye sahip müşteriyi günceller.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Müşteri başarıyla güncellendi."),
        @ApiResponse(responseCode = "404", description = "Müşteri bulunamadı."),
        @ApiResponse(responseCode = "400", description = "Geçersiz müşteri verisi.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @Parameter(description = "Müşteri ID'si", example = "customer123")
            @PathVariable String id,
            @Parameter(description = "Güncellenmiş müşteri bilgileri", required = true)
            @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        if (updatedCustomer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedCustomer);
    }

    @Operation(summary = "Müşteri sil", description = "Belirtilen ID'ye sahip müşteriyi siler.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Müşteri başarıyla silindi."),
        @ApiResponse(responseCode = "404", description = "Müşteri bulunamadı.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(
            @Parameter(description = "Müşteri ID'si", example = "customer123")
            @PathVariable String id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}