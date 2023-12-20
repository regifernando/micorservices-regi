package com.order.service.orderservice.controller;

import com.order.service.orderservice.model.entity.Order;
import com.order.service.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    public Object save(@RequestBody Order order){
        return orderService.save(order);
    }
    @GetMapping("/detail/{id}")
    public Object findById(@PathVariable("id") Long id){
        return orderService.findByOrderIdNew(id);
    }

    @GetMapping("/order-number/{orderNumber}")
    public Object findByOrderNumber(@PathVariable("orderNumber") String orderNumber){
        return orderService.findByOrderNumber(orderNumber);
    }
}
