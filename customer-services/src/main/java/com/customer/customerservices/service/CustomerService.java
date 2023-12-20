package com.customer.customerservices.service;

import com.customer.customerservices.model.entity.Customer;
import com.customer.customerservices.model.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer findById(Long id){
        return customerRepository.findById(id).orElse(null);
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer findByEmail(String email){
        return customerRepository.findByEmail(email);
    }
}
