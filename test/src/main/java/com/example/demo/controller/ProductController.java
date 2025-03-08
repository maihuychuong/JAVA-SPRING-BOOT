package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String listProducts(
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            @RequestParam(value = "page", defaultValue = "1") int page,
            Model model) {

        List<Product> allProducts = productService.getAllProducts();

        // Lọc theo từ khóa tìm kiếm (nếu có)
        if (!keyword.isEmpty()) {
            allProducts = allProducts.stream()
                    .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()))
                    .toList();
        }

        int pageSize = 12; // Số sản phẩm mỗi trang
        int totalProducts = allProducts.size();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        // Lấy danh sách sản phẩm của trang hiện tại
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, totalProducts);
        List<Product> productsPage = allProducts.subList(fromIndex, toIndex);

        model.addAttribute("products", productsPage);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "products";
    }


    @GetMapping("/products/{id}")
    public String productDetail(@PathVariable int id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isEmpty()) {
            return "redirect:/products";
        }
        model.addAttribute("product", product.get());
        return "product-detail";
    }
}
