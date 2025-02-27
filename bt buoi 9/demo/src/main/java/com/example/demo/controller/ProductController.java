package com.example.demo.controller;

import com.example.demo.model.Product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    private List<Product> products = new ArrayList<>(List.of(
            new Product("1", "iPhone 13", "Latest iPhone model", 999, "Apple"),
            new Product("2", "Samsung Galaxy S22", "High-end Samsung phone", 899, "Samsung"),
            new Product("3", "MacBook Air M2", "Lightweight and powerful", 1299, "Apple"),
            new Product("4", "Dell XPS 15", "Premium Windows laptop", 1599, "Dell"),
            new Product("5", "Sony WH-1000XM5", "Noise-canceling headphones", 399, "Sony")
    ));

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return products;
    }
}
