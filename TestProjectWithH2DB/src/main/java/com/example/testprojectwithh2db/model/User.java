package com.example.testprojectwithh2db.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@Data
public class User {
    String name;

    @Autowired
    Password password;

    @Bean(autowireCandidate = true)
    public User getUser() {
        return this;
    }
}
