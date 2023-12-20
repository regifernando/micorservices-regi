package com.order.service.orderservice.webclient;
/*
IntelliJ IDEA 2023.2.3 (Ultimate Edition)
Build #IU-232.10072.27, built on October 11, 2023
@Author Admin a.k.a. Regi Fernando
Java Developer
Created on 12/7/2023 10:18 AM
@Last Modified 12/7/2023 10:18 AM
Version 1.0
*/

import com.order.service.orderservice.dto.Customer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface CustomerClient {
//    @GetExchange("/api/v1/customer/detail/{id}")
//    @Retryable(maxAttempts = 60, backoff = @Backoff(delay = 1000))
//    public Customer fi(@PathVariable("id") Long id);

    @GetExchange("/api/v1/customer/detail/{id}")
    public Customer findById(@PathVariable("id") Long id);
}
