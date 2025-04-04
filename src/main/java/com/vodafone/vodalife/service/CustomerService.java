package com.vodafone.vodalife.service;

import com.vodafone.vodalife.model.Customer;
import com.vodafone.vodalife.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Yeni müşteri oluştur
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Tüm müşterileri getir
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // ID'ye göre müşteri getir
    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }

    // Müşteri güncelle
    public Customer updateCustomer(String id, Customer updatedCustomer) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            Customer customer = existingCustomer.get();
            customer.setName(updatedCustomer.getName());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
            return customerRepository.save(customer);
        }
        return null; // Eğer müşteri bulunamazsa null döner
    }

    // Müşteri sil
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }

    //// LOGIN
    public Optional<Customer> getCustomerByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber);
    }

    public Customer updateCustomerPoints(String customerId, int pointsToAdd) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setTotalPoints(customer.getTotalPoints() + pointsToAdd);
            return customerRepository.save(customer);
        }
        return null;
    }
}