package com.vodafone.vodalife.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document(collection = "bill_tracker") // MongoDB'de "bill_tracker" koleksiyonunda saklanacak
public class BillTracker {

    @Id
    private String id; // MongoDB'nin otomatik oluşturduğu ID
    private String userId; // Kullanıcı ID'si (Customer ile ilişkilendirme)
    private Map<String, List<BillRecord>> billsByMonth = new HashMap<>(); // Ay bazında gruplanmış faturalar

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, List<BillRecord>> getBillsByMonth() {
        return billsByMonth;
    }

    public void setBillsByMonth(Map<String, List<BillRecord>> billsByMonth) {
        this.billsByMonth = billsByMonth;
    }
}