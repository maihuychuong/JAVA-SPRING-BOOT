package com.example.demo.controller;

import com.example.demo.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class WebController {
    private List<Person> people = new ArrayList<>();

    public WebController() {
        loadPeopleFromCSV("src/main/resources/person.csv");
    }

    private void loadPeopleFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] data = line.split(",");

                int id = Integer.parseInt(data[0].trim());
                String fullname = data[1].trim();
                String job = data[2].trim();
                String gender = data[3].trim();
                String city = data[4].trim();
                int salary = Integer.parseInt(data[5].trim());
                LocalDate birthday = LocalDate.parse(data[6].trim(), formatter);

                people.add(new Person(id, fullname, job, gender, city, salary, birthday));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 1. Liệt kê thành phố và danh sách người sống ở đó
    @GetMapping("/groupPeopleByCity")
    public String groupPeopleByCity(Model model) {
        Map<String, List<Person>> groupedByCity = people.stream()
                .collect(Collectors.groupingBy(Person::getCity));

        model.addAttribute("groupedByCity", groupedByCity);
        return "groupPeopleByCity";
    }

    // 2. Liệt kê danh sách nghề nghiệp và số người làm nghề đó
    @GetMapping("/groupJobByCount")
    public String groupJobByCount(Model model) {
        Map<String, Long> jobCount = people.stream()
                .collect(Collectors.groupingBy(Person::getJob, Collectors.counting()));

        model.addAttribute("jobCount", jobCount);
        return "groupJobByCount";
    }

    // 3. Liệt kê danh sách người có mức lương lớn hơn mức lương trung bình
    @GetMapping("/aboveAverageSalary")
    public String aboveAverageSalary(Model model) {
        double averageSalary = people.stream().mapToInt(Person::getSalary).average().orElse(0);
        List<Person> aboveAverage = people.stream()
                .filter(person -> person.getSalary() > averageSalary)
                .collect(Collectors.toList());

        model.addAttribute("averageSalary", averageSalary);
        model.addAttribute("aboveAverage", aboveAverage);
        return "aboveAverageSalary";
    }

    // 4. Hiển thị người có độ dài tên dài nhất
    @GetMapping("/longestName")
    public String longestName(Model model) {
        Person longestNamePerson = people.stream()
                .max(Comparator.comparingInt(person -> person.getFullname().length()))
                .orElse(null);

        model.addAttribute("longestNamePerson", longestNamePerson);
        return "longestName";
    }
}
