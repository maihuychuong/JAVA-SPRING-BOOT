package com.example.demo.controller;

import com.example.demo.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {
    private List<Person> people = new ArrayList<>(List.of(
            new Person(1, "A", "M", 12),
            new Person(2, "B", "F", 24)
    ));
    @GetMapping("/") // http://localhost:8080
    public String getHome(Model model){
        model.addAttribute("people", people);
        model.addAttribute("person", people.get(0));

        model.addAttribute("name", "Nguyen Van A");
        model.addAttribute("salary", 1000);
        return "index";
    }
}
