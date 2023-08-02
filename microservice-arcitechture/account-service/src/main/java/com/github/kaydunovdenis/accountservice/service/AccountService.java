package com.github.kaydunovdenis.accountservice.service;

import com.github.kaydunovdenis.accountservice.model.Account;
import com.github.kaydunovdenis.accountservice.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account save(Account account) {
        return repository.save(account);
    }
}
