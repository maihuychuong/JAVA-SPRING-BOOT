package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

	//	C1: Sử dụng implements
        Greeting vietnam = new Vietnam();
        vietnam.sayHello("Nguyen Van A");

	//	C2: Sử dụng anonymous class
        Greeting china = new Greeting() {
            @Override
            public void sayHello(String name) {
                System.out.println("你好 " + name);
            }

        };
        china.sayHello("Tran Van B");

	//	C3: Lambda Expression
        Greeting english = (name) -> System.out.println("Hello " + name);
        english.sayHello("Le Thi C");

		Calculator sum = (a, b) -> a + b;
		System.out.println("Sum " + sum.calculate(3, 5));

        List<Integer> numbers = List.of(10, 5, 3, 12, 6, 7, 5, 3);

//        1. Duyệt numbers
        numbers.stream().forEach(number -> System.out.println(number));

//        2. Tìm các giá trị chẵn trong list
        List<Integer> evenNumbers = numbers.stream().filter(n -> n % 2 == 0).toList();
        System.out.println(evenNumbers);

//        3. Tìm các giá trị > 5 trong list
        List<Integer> greaterThanFive = numbers.stream().filter(n -> n > 5).toList();
        System.out.println(greaterThanFive);

//        4. Tìm giá trị max trong list
        Optional<Integer> max = numbers.stream().max(Integer::compareTo);
        System.out.println(max);

//        5. Tìm giá trị min trong list
        Optional<Integer> min = numbers.stream().min(Integer::compareTo);
        System.out.println(min);

//        6. Tính tổng các phần tử của mảng
        int sumNum = numbers.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sumNum);

//        7. Lấy danh sách các phần tử không trùng nhau
        List<Integer> distinctNums = numbers.stream().distinct().toList();
        System.out.println(distinctNums);

//        8. Lấy 5 phần tử đầu tiên trong mảng
        List<Integer> limitedNumbers = numbers.stream().limit(5).toList();
        System.out.println(limitedNumbers);

//        9. Lấy phần tử từ thứ 3 -> thứ 5
        List<Integer> skippedNumbers = numbers.stream().limit(5).skip(2).toList();
        System.out.println(skippedNumbers);

//        10. Lấy phần tử đầu tiên > 5
        Optional<Integer> fristNumGreaterThanFive = numbers.stream().filter(n -> n > 5).findFirst();
        System.out.println(fristNumGreaterThanFive);

//        11. Kiểm tra xem list có phải là list chẵn hay không
//        12. Kiểm tra xem list có phần tử  > 10 hay không
//        13. Có bao nhiêu phần tử  > 5
//        14. Nhân đôi các phần tủ trong list và trả về list mới
//        15. Kiểm tra xem list không chứa giá trị nào = 8 hay không

    }
//	Functional Interface là interface chứa duy nhất 1 phương thức abstract
//	Lambda Expression dùng để triển khai Functional Interface
}

