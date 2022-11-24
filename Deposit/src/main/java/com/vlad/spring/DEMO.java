package com.vlad.spring;

import com.vlad.spring.Demo.APP;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
public class DEMO {
        public static void main(String[] args) {
            SpringApplication.run(DEMO.class, args);
            System.out.println(1);
            APP consolApp= new APP();
            System.out.println(2);
        }
    }

