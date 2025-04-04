package com.vodafone.vodalife.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoDBInitializer {

    private final MongoClient mongoClient;
    private final MongoTemplate mongoTemplate;

    public MongoDBInitializer(MongoClient mongoClient, MongoTemplate mongoTemplate) {
        this.mongoClient = mongoClient;
        this.mongoTemplate = mongoTemplate;
    }
    
    @Bean
    CommandLineRunner initMongoDB() {
        return args -> {
            // 1. MongoDB admin veritabanına bağlan ve kullanıcı oluştur
            MongoDatabase adminDb = mongoClient.getDatabase("admin");
            try {
                // Kullanıcıyı oluşturmayı dene
                adminDb.runCommand(new org.bson.Document("createUser", "admin")
                    .append("pwd", "password")
                    .append("roles", java.util.Arrays.asList(
                        new org.bson.Document("role", "readWrite").append("db", "vodalife")
                    ))
                );
                System.out.println("Kullanıcı başarıyla oluşturuldu.");
            } catch (Exception e) {
                // Kullanıcı zaten mevcutsa hata alırsınız, bu durumda hata mesajını yazdırabilirsiniz
                if (e.getMessage().contains("User already exists")) {
                    System.out.println("Kullanıcı zaten mevcut.");
                } else {
                    System.out.println("Kullanıcı oluşturulurken bir hata oluştu: " + e.getMessage());
                }
            }
        
            // 2. "vodalife" veritabanını kontrol et ve yoksa oluştur
            try {
                boolean databaseExists = mongoClient.listDatabaseNames()
                                                    .into(new java.util.ArrayList<>())
                                                    .contains("vodalife");
                if (!databaseExists) {
                    mongoClient.getDatabase("vodalife").createCollection("customers");
                    mongoClient.getDatabase("vodalife").createCollection("step_tracker");
                    mongoClient.getDatabase("vodalife").createCollection("bill_tracker");
                    mongoClient.getDatabase("vodalife").createCollection("recycling_points");
                    mongoClient.getDatabase("vodalife").createCollection("questions");
                    mongoClient.getDatabase("vodalife").createCollection("events");
                    mongoClient.getDatabase("vodalife").createCollection("rewards");

                    // mongoClient.getDatabase("vodalife").getCollection("temp").drop();
                    System.out.println("Vodalife veritabanı oluşturuldu.");
                } else {
                    System.out.println("Vodalife veritabanı zaten mevcut.");
                }
            } catch (Exception e) {
                System.out.println("Vodalife veritabanı oluştururken bir sorun ile karşılaşıldı.");
            }

            // 3. "customers" koleksiyonunun var olup olmadığını kontrol et ve yoksa oluştur
            try {
                if (!mongoTemplate.collectionExists("customers")) {
                    mongoClient.getDatabase("vodalife").createCollection("customers");
                    System.out.println("Customers koleksiyonu başarıyla oluşturuldu.");
                } else {
                    System.out.println("Customers koleksiyonu zaten mevcut.");
                }
            } catch (Exception e) {
                System.out.println("Customers koleksiyonu kontrol edilirken bir hata oluştu: " + e.getMessage());
            }

            // 4. "step_tracker" koleksiyonunun var olup olmadığını kontrol et ve yoksa oluştur
            try {
                if (!mongoTemplate.collectionExists("step_tracker")) {
                    mongoClient.getDatabase("vodalife").createCollection("step_tracker");
                    System.out.println("StepTracker koleksiyonu başarıyla oluşturuldu.");
                } else {
                    System.out.println("StepTracker koleksiyonu zaten mevcut.");
                }
            } catch (Exception e) {
                System.out.println("StepTracker koleksiyonu kontrol edilirken bir hata oluştu: " + e.getMessage());
            }

            // 5. "bill_tracker" koleksiyonunun var olup olmadığını kontrol et ve yoksa oluştur
            try {
                if (!mongoTemplate.collectionExists("bill_tracker")) {
                    mongoClient.getDatabase("vodalife").createCollection("bill_tracker");
                    System.out.println("BillTracker koleksiyonu başarıyla oluşturuldu.");
                } else {
                    System.out.println("BillTracker koleksiyonu zaten mevcut.");
                }
            } catch (Exception e) {
                System.out.println("BillTracker koleksiyonu kontrol edilirken bir hata oluştu: " + e.getMessage());
            }

            // 6. "recycling_points" koleksiyonunun var olup olmadığını kontrol et ve yoksa oluştur
            try {
                if (!mongoTemplate.collectionExists("recycling_points")) {
                    mongoClient.getDatabase("vodalife").createCollection("recycling_points");
                    System.out.println("RecyclingPoints koleksiyonu başarıyla oluşturuldu.");
                } else {
                    System.out.println("RecyclingPoints koleksiyonu zaten mevcut.");
                }
            } catch (Exception e) {
                System.out.println("RecyclingPoints koleksiyonu kontrol edilirken bir hata oluştu: " + e.getMessage());
            }

            // 7. "questions" koleksiyonunun var olup olmadığını kontrol et ve yoksa oluştur
            try {
                if (!mongoTemplate.collectionExists("questions")) {
                    mongoClient.getDatabase("vodalife").createCollection("questions");
                    System.out.println("Questions koleksiyonu başarıyla oluşturuldu.");
                } else {
                    System.out.println("Questions koleksiyonu zaten mevcut.");
                }
            } catch (Exception e) {
                System.out.println("Questions koleksiyonu kontrol edilirken bir hata oluştu: " + e.getMessage());
            }
            
            // 8. "events" koleksiyonunun var olup olmadığını kontrol et ve yoksa oluştur
            try {
                if (!mongoTemplate.collectionExists("events")) {
                    mongoClient.getDatabase("vodalife").createCollection("events");
                    System.out.println("Events koleksiyonu başarıyla oluşturuldu.");
                } else {
                    System.out.println("Events koleksiyonu zaten mevcut.");
                }
            } catch (Exception e) {
                System.out.println("Events koleksiyonu kontrol edilirken bir hata oluştu: " + e.getMessage());
            }

            // 9. "rewards" koleksiyonunun var olup olmadığını kontrol et ve yoksa oluştur
            try {
                if (!mongoTemplate.collectionExists("rewards")) {
                    mongoClient.getDatabase("vodalife").createCollection("rewards");
                    System.out.println("Rewards koleksiyonu başarıyla oluşturuldu.");
                } else {
                    System.out.println("Rewards koleksiyonu zaten mevcut.");
                }
            } catch (Exception e) {
                System.out.println("Rewards koleksiyonu kontrol edilirken bir hata oluştu: " + e.getMessage());
            }
        };
    }
}