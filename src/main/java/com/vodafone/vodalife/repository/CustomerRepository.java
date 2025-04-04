package com.vodafone.vodalife.repository;

import com.vodafone.vodalife.model.Customer;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    // MongoDB'de CRUD işlemleri için hazır metotlar sağlar
    Optional<Customer> findByPhoneNumber(String phoneNumber);
}

