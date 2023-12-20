package com.order.service.orderservice.service;

import com.order.service.orderservice.dto.Customer;
import com.order.service.orderservice.dto.OrderLineResponse;
import com.order.service.orderservice.dto.OrderResponse;
import com.order.service.orderservice.dto.Product;
import com.order.service.orderservice.model.entity.Order;
import com.order.service.orderservice.model.entity.OrderLine;
import com.order.service.orderservice.model.repository.OrderRepository;
import com.order.service.orderservice.webclient.CustomerClient;
import com.order.service.orderservice.webclient.ProductClient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CustomerClient customerClient;
    @Autowired
    private ProductClient productClient;

    public Order save(Order order) {
        for (OrderLine orderLine : order.getOrderLines()) {
            orderLine.setOrder(order);
        }
        return orderRepository.save(order);
    }

    public OrderResponse findByOrderId(Long id){
        try{

            Optional<Order> optOrder = orderRepository.findById(id);
            if(!optOrder.isPresent()){
                return null;
            }
            Order order = optOrder.get();
            Customer customer = customerClient.findById(order.getCustomerId());
            OrderResponse orderResponse = new OrderResponse(
                    order.getId(),
                    order.getOrderNumber(),
                    order.getOrderDate(),
                    customer,
                    new ArrayList<OrderLineResponse>());
            for(OrderLine orderLine : order.getOrderLines()){
                Product product = findProductById(orderLine.getProductId());

                orderResponse.getOrderLineResponses().add(new OrderLineResponse(
                        orderLine.getId(),
                        product,
                        orderLine.getQuantity(),
                        orderLine.getPrice())
                );
            }
            return orderResponse;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        //return orderRepository.findById(id).orElse(null);
    }

    public OrderResponse findByOrderNumber(String orderNumber){
        Order order = orderRepository.findByOrderNumber(orderNumber);
        if(order == null){
            return null;
        }

        OrderResponse orderResponse = new OrderResponse(
                order.getId(),
                order.getOrderNumber(),
                order.getOrderDate(),
                findCustomerById(order.getCustomerId()),
                new ArrayList<OrderLineResponse>());
        for(OrderLine orderLine : order.getOrderLines()){
            Product product = productClient.findById(orderLine.getProductId());//findProductById(orderLine.getProductId());

            orderResponse.getOrderLineResponses().add(new OrderLineResponse(
                    orderLine.getId(),
                    product,
                    orderLine.getQuantity(),
                    orderLine.getPrice())
            );
        }

        return orderResponse;
        //return orderRepository.findById(id).orElse(null);
    }

    public Customer findCustomerById(Long id){
        return restTemplate.getForObject("http://CUSTOMER-SERVICE/api/v1/customer/detail/"+id, Customer.class);
    }

    public Product findProductById(Long id){
        return restTemplate.getForObject("http://PRODUCT-SERVICE/api/v1/product/detail/"+id, Product.class);
    }

    public OrderResponse findByOrderIdNew(Long id) {
        try {
            Optional<Order> optOrder = orderRepository.findById(id);
            Order order = optOrder.orElse(null);

            if (order == null) {
                return null;
            }

            //CompletableFuture<Customer> customer = findCustomerByIdNew(order.getCustomerId());

            OrderResponse orderResponse = new OrderResponse(
                    order.getId(),
                    order.getOrderNumber(),
                    order.getOrderDate(),
                    findCustomerByIdNew(order.getCustomerId()).get(), // Asynchronous call
                    new ArrayList<OrderLineResponse>());

            for (OrderLine orderLine : order.getOrderLines()) {
                Product product = findProductById(orderLine.getProductId());

                orderResponse.getOrderLineResponses().add(new OrderLineResponse(
                        orderLine.getId(),
                        product,
                        orderLine.getQuantity(),
                        orderLine.getPrice())
                );
            }

            return orderResponse;
        } catch (Exception e) {
            //log.error("Error processing findByOrderId for orderId: {}", id, e);
            throw new RuntimeException("Error processing findByOrderId: " + e.getMessage());
        }
    }

    @Async
    public CompletableFuture<Customer> findCustomerByIdNew(Long id) {
        return CompletableFuture.completedFuture(customerClient.findById(id));
    }

}
