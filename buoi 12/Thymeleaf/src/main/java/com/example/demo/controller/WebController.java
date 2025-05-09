package com.example.demo.controller;

import com.example.demo.model.PageResponese;
import com.example.demo.model.Person;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Controller
public class WebController {
    private List<Person> people = new ArrayList<>();

    public WebController(){
        Faker faker = new Faker();
        Random rd = new Random();
        for (int i = 0; i < 55; i++){
            Person person = new Person(
                    i + 1,
                    faker.name().fullName(),
                    rd.nextInt(2) == 1 ? "M" : "F",
                    faker.number().numberBetween(18, 60)
            );
            people.add(person);
        }
    }

    @GetMapping("/") // http://localhost:8080
    public String getHome(Model model, @RequestParam(required = false) String keyword,  @RequestParam(required = false, defaultValue = "1") int page){
        List<Person> peopleFound = new ArrayList<>();
        if(keyword != null){
            peopleFound = people.stream()
                    .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()))
                    .toList();
        } else {
            peopleFound = people;
        }

        PageResponese<Person> pageResponese = new PageResponese<>(peopleFound, 10, page);
        model.addAttribute("pageResponese", pageResponese);
        model.addAttribute("people", peopleFound);
        model.addAttribute("person", people.get(0));

        model.addAttribute("name", "Nguyen Van A");
        model.addAttribute("salary", 1000);
        return "index";
    }

    @GetMapping("/person/{id}") // http://localhost:8080/person/1
    public String getPerson(Model model, @PathVariable int id) {
        Person person = people.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        model.addAttribute("person", person);
        return "person";
    }
}
