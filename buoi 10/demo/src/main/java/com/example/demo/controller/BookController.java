package com.example.demo.controller;

import com.example.demo.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RestController // Đánh dấu lên class -> Class này sẽ xử lý request và response (controller)controller
public class BookController {
    private List<Book> books = new ArrayList<>(List.of(
            new Book("OX-13", "Gone with the wind", "Cuong", 1945),
            new Book("OX-14", "Chi Dau", "Nam Cao", 1943)
    ));

    // 1. Lấy danh sách book GET - /book
    @GetMapping("/books") // HTTP method + API URL
    public List<Book> getAllBooks(){
        return books;
    }
    
    // 2. Lấy chi tiết book theo id GET - /book/{id}
    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable String id){
        for (Book book : books){
            if(book.getId().equals(id)){
                return book;
            }
        }
        return null;
    }

}
