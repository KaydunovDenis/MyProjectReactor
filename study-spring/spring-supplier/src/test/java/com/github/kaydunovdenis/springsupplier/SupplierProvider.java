package com.github.kaydunovdenis.springsupplier;

import com.github.kaydunovdenis.springsupplier.entity.Address;
import com.github.kaydunovdenis.springsupplier.entity.Order;
import com.github.kaydunovdenis.springsupplier.entity.Product;
import com.github.kaydunovdenis.springsupplier.entity.Recipient;
import com.github.kaydunovdenis.springsupplier.entity.Supplier;

import java.util.List;
import java.util.Set;

public class SupplierProvider {

    public static Supplier createSupplier() {
        Address address = new Address("Belarus", "Gomel", "Covetskaya", 32);

        Product product = new Product("Product1", 100);
        List<Product> products = List.of(product);

        Order order = new Order("Order1", 200);
        order.setProducts(products);
        Set<Order> orders = Set.of(order);

        Recipient recipient = new Recipient("Recipient1");
        Set<Recipient> recipients = Set.of(recipient);

        Supplier supplier = new Supplier("Supplier1", address);
        supplier.setOrders(orders);
        supplier.setRecipients(recipients);
        return supplier;
    }
}
