package com.github.kaydunovdenis.accountservice.repository;

import com.github.kaydunovdenis.accountservice.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {

    public Account findAccountById(String id);
}
