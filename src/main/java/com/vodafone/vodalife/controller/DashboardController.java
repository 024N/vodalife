package com.vodafone.vodalife.controller;

import com.vodafone.vodalife.model.Customer;
import com.vodafone.vodalife.model.StepTracker;
import com.vodafone.vodalife.model.StepRecord;
import com.vodafone.vodalife.model.BillTracker;
import com.vodafone.vodalife.model.BillRecord;
import com.vodafone.vodalife.service.CustomerService;
import com.vodafone.vodalife.service.StepTrackerService;
import com.vodafone.vodalife.service.BillTrackerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private StepTrackerService stepTrackerService;

    @Autowired
    private BillTrackerService billTrackerService;

    @Operation(summary = "Kullanıcının dashboard bilgilerini getir", description = "Kullanıcının genel bilgilerini (örneğin, kullanıcı bilgileri, adım kayıtları, fatura kayıtları) döner.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Bilgiler başarıyla getirildi."),
        @ApiResponse(responseCode = "404", description = "Müşteri bulunamadı.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getDashboard(
            @Parameter(description = "Müşteri ID'si", example = "customer123")
            @PathVariable String id) {
        // Kullanıcı bilgilerini al
        Optional<Customer> customerOptional = customerService.getCustomerById(id);
        if (customerOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Müşteri bulunamadı.");
        }

        Customer customer = customerOptional.get();

        // StepTrackerService'i kullanarak adım kayıtlarını al
        StepTracker stepTracker = stepTrackerService.getStepTrackerByUserId(id);
        List<StepRecord> stepRecords = stepTracker.getStepRecords();

        // BillTrackerService'i kullanarak fatura kayıtlarını al
        BillTracker billTracker = billTrackerService.getBillsByUserId(id);
        List<BillRecord> billRecords = billTracker.getBillsByMonth().values().stream()
                .flatMap(List::stream)
                .toList();

        // Kullanıcı bilgilerini bir JSON nesnesi olarak döndür
        Map<String, Object> dashboardInfo = new HashMap<>();
        dashboardInfo.put("customerId", customer.getId());
        dashboardInfo.put("name", customer.getName());
        dashboardInfo.put("email", customer.getEmail());
        dashboardInfo.put("phoneNumber", customer.getPhoneNumber());
        dashboardInfo.put("totalPoints", customer.getTotalPoints());
        dashboardInfo.put("stepRecords", stepRecords); // Adım kayıtları
        dashboardInfo.put("billRecords", billRecords); // Fatura kayıtları

        return ResponseEntity.ok(dashboardInfo);
    }
}