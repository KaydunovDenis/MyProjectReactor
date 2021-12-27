package com.apress.spring.old;

import com.apress.spring.SpringBootSimpleApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;


//@SpringBootApplication
public class SpringBootSimpleApplication2 {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(SpringBootSimpleApplication.class, args);
    }
}

@Component
class MyComponent {
    private static final Logger log = LoggerFactory.getLogger(MyComponent.class);

    private MyComponent() {
        throw new IllegalStateException("Utility class");
    }

    @Autowired
    public MyComponent(ApplicationArguments args) {
        boolean enable = args.containsOption("enable");
        if (enable) log.info("## > You are enabled!");
        List<String> listArgs = args.getNonOptionArgs();
        log.info("## > extra args ...");
        if(!listArgs.isEmpty()) listArgs.forEach(log::info);
    }
}