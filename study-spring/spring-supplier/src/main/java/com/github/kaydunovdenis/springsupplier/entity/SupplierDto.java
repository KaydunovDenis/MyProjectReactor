package com.github.kaydunovdenis.springsupplier.entity;

import java.util.List;
import java.util.Set;

public class SupplierDto {
    private String name;
    private Address address;
    private List<Order> orders;
    private Set<Recipient> recipients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Set<Recipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(Set<Recipient> recipients) {
        this.recipients = recipients;
    }
}
