package com.example.demo.controller;

import com.example.demo.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private List<Product> products = new ArrayList<>(List.of(
            new Product("1", "iPhone 13", "Latest iPhone model", 999, "Apple"),
            new Product("2", "Samsung Galaxy S22", "High-end Samsung phone", 899, "Samsung"),
            new Product("3", "MacBook Air M2", "Lightweight and powerful", 1299, "Apple"),
            new Product("4", "Dell XPS 15", "Premium Windows laptop", 1599, "Dell"),
            new Product("5", "Sony WH-1000XM5", "Noise-canceling headphones", 399, "Sony")
    ));

    // 1. Lấy thông tin chi tiết của một sản phẩm
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 2. Lấy sản phẩm với tên bắt đầu bằng prefix nào đó
    @GetMapping("/name-starts/{prefix}")
    public ResponseEntity<List<Product>> getProductsByPrefix(@PathVariable String prefix) {
        List<Product> result = products.stream()
                .filter(product -> product.getName().toLowerCase().startsWith(prefix.toLowerCase()))
                .toList();
        return ResponseEntity.ok(result);
    }
    // 3. Lọc sản phẩm theo khoảng giá
    @GetMapping("/price/{min}/{max}")
    public ResponseEntity<List<Product>> getProductsByPriceRange(@PathVariable int min, @PathVariable int max) {
        List<Product> result = products.stream()
                .filter(product -> product.getPrice() >= min && product.getPrice() <= max)
                .toList();
        return ResponseEntity.ok(result);
    }

    // 4. Lấy sản phẩm theo thương hiệu
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable String brand) {
        List<Product> result = products.stream()
                .filter(product -> product.getBrand().equalsIgnoreCase(brand))
                .toList();
        return ResponseEntity.ok(result);
    }

    // 5. Lấy sản phẩm có giá cao nhất theo brand
    @GetMapping("/brand/{brand}/max-price")
    public ResponseEntity<Product> getMostExpensiveProductByBrand(@PathVariable String brand) {
        return products.stream()
                .filter(product -> product.getBrand().equalsIgnoreCase(brand))
                .max(Comparator.comparingInt(Product::getPrice))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
