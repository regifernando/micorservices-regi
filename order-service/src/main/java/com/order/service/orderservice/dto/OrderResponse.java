package com.order.service.orderservice.dto;

import java.util.Date;
import java.util.List;

public class OrderResponse {

    private Long id;
    private String orderNumber;
    private Date orderDate;
    private Customer customer;
    private List<OrderLineResponse> orderLineResponses;
    public OrderResponse() {
    }

    public OrderResponse(Long id, String orderNumber, Date orderDate, Customer customer, List<OrderLineResponse> orderLineResponses) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.customer = customer;
        this.orderLineResponses = orderLineResponses;
    }

    public List<OrderLineResponse> getOrderLineResponses() {
        return orderLineResponses;
    }

    public void setOrderLineResponses(List<OrderLineResponse> orderLineResponses) {
        this.orderLineResponses = orderLineResponses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
