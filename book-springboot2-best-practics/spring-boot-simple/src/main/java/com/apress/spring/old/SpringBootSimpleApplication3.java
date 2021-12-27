package com.apress.spring.old;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//@SpringBootApplication
public class SpringBootSimpleApplication3 implements CommandLineRunner, ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(SpringBootSimpleApplication3.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSimpleApplication3.class, args);
    }

    @Bean
    String info() {
        return "Just a simple String bean";
    }

    @Autowired
    String info;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("## > ApplicationRunner Implementation...");
        log.info("Accessing the Info bean: " + info);
        args.getNonOptionArgs().forEach(file -> log.info(file));
    }

    @Override
    public void run(String... args) {
        log.info("## > CommandLineRunner Implementation...");
        log.info("Accessing the Info bean: " + info);
        for (String arg : args)
            log.info(arg);
    }
}