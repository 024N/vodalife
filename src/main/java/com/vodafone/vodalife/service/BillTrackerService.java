package com.vodafone.vodalife.service;

import com.vodafone.vodalife.model.BillRecord;
import com.vodafone.vodalife.model.BillTracker;
import com.vodafone.vodalife.repository.BillTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BillTrackerService {

    @Autowired
    private BillTrackerRepository billTrackerRepository;

    public BillTracker addBill(String userId, String type, double value, String unit, double cost, String month) {
        BillTracker billTracker = billTrackerRepository.findByUserId(userId)
                .orElseGet(() -> {
                    BillTracker newTracker = new BillTracker();
                    newTracker.setUserId(userId);
                    return newTracker;
                });

        // Ay bazında faturaları al
        List<BillRecord> recordsForMonth = billTracker.getBillsByMonth()
                .computeIfAbsent(month, k -> new ArrayList<>());

        // Belirtilen tür için fatura kaydını kontrol et
        Optional<BillRecord> existingRecord = recordsForMonth.stream()
                .filter(record -> record.getType().equalsIgnoreCase(type))
                .findFirst();

        if (existingRecord.isPresent()) {
            // Mevcut kaydı güncelle
            BillRecord record = existingRecord.get();
            record.setValue(value);
            record.setUnit(unit);
            record.setCost(cost);
        } else {
            // Yeni bir kayıt ekle
            recordsForMonth.add(new BillRecord(month, type, value, unit, cost));
        }

        return billTrackerRepository.save(billTracker);
    }

    public BillTracker getBillsByUserId(String userId) {
        return billTrackerRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı."));
    }
}