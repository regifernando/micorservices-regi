package com.order.service.orderservice.webclient;
/*
IntelliJ IDEA 2023.2.3 (Ultimate Edition)
Build #IU-232.10072.27, built on October 11, 2023
@Author Admin a.k.a. Regi Fernando
Java Developer
Created on 12/18/2023 7:34 AM
@Last Modified 12/18/2023 7:34 AM
Version 1.0
*/

import com.order.service.orderservice.dto.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface ProductClient {
    @GetExchange("/api/v1/product/detail/{id}")
    public Product findById(@PathVariable("id") Long id);
}
