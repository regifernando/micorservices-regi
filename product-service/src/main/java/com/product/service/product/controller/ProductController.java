package com.product.service.product.controller;

import com.product.service.product.model.entity.Product;
import com.product.service.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public Object findAll(){
        return productService.findAll();
    }

    @PostMapping("/create")
    public Object save(@RequestBody Product product){
        return productService.save(product);
    }

    @GetMapping("/detail/{id}")
    public Object findById(@PathVariable("id") Long id){
        return productService.detail(id);
    }
}
