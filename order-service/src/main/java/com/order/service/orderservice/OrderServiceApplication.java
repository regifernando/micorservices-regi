package com.order.service.orderservice;

import com.order.service.orderservice.webclient.CustomerClient;
import com.order.service.orderservice.webclient.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Autowired
	private LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction;

	@Bean
	WebClient webClientCustomer(){
		return WebClient.builder()
				.baseUrl("http://customer-service")
				.filter(loadBalancedExchangeFilterFunction)
				.build();
	}

	@Bean
	WebClient webClientProduct(){
		return WebClient.builder()
				.baseUrl("http://product-service")
				.filter(loadBalancedExchangeFilterFunction)
				.build();
	}

	@Bean
	CustomerClient customerClient() {
		HttpServiceProxyFactory factory = HttpServiceProxyFactory
				.builder(WebClientAdapter.forClient(webClientCustomer())).build();

		return factory.createClient(CustomerClient.class);
	}

	@Bean
	ProductClient productClient(){
		HttpServiceProxyFactory factory = HttpServiceProxyFactory
				.builder(WebClientAdapter.forClient(webClientProduct())).build();

		return factory.createClient(ProductClient.class);
	}
}
