package com.example.demo.repository;

import com.example.demo.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private final List<Product> products;

    public ProductRepository() {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = new ClassPathResource("products.json").getInputStream()) {
            products = mapper.readValue(inputStream, new TypeReference<List<Product>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Không thể đọc dữ liệu sản phẩm", e);
        }
    }

    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> findById(int id) {
        return products.stream().filter(p -> p.getId() == id).findFirst();
    }
}
