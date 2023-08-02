package com.github.kaydunovdenis.accountservice.model;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Account {

    @Id
    private UUID id;
    private String name;
    private String description;
    private Date dateLastSeen;
    private List<Income> incomes = new ArrayList<>();
    private List<Expanse> expanses = new ArrayList<>();
    private Set<Deposit> deposits = new HashSet<>();

    public Account() {}

    public Account(UUID id, String name, String description, Date dateLastSeen, List<Income> incomes, List<Expanse> expanses, Set<Deposit> deposits) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateLastSeen = dateLastSeen;
        this.incomes = incomes;
        this.expanses = expanses;
        this.deposits = deposits;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateLastSeen() {
        return dateLastSeen;
    }

    public void setDateLastSeen(Date dateLastSeen) {
        this.dateLastSeen = dateLastSeen;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }

    public List<Expanse> getExpanses() {
        return expanses;
    }

    public void setExpanses(List<Expanse> expanses) {
        this.expanses = expanses;
    }

    public Set<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(Set<Deposit> deposits) {
        this.deposits = deposits;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateLastSeen=" + dateLastSeen +
                ", incomes=" + incomes +
                ", expanses=" + expanses +
                ", deposits=" + deposits +
                '}';
    }
}
