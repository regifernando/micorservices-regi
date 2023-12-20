package com.customer.customerservices.controller;

import com.customer.customerservices.model.entity.Customer;
import com.customer.customerservices.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public Object save(@RequestBody Customer customer){
        return customerService.save(customer);
    }

    @GetMapping("/detail/{id}")
    public Customer detailById(@PathVariable("id") Long id){
        return customerService.findById(id);
    }

    @GetMapping("/all")
    public Object findAll(){
        return customerService.findAll();
    }

    @GetMapping("/email/{email}")
    public Object findByEmail(@PathVariable("email") String email){
        return customerService.findByEmail(email);
    }
}
