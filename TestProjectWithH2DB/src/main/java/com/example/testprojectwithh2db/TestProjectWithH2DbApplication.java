package com.example.testprojectwithh2db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SpringBootApplication()
public class TestProjectWithH2DbApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestProjectWithH2DbApplication.class, args);
        System.out.println("Hello World!");

        Map set = new HashMap();
        new HashSet<>();
    }

}
