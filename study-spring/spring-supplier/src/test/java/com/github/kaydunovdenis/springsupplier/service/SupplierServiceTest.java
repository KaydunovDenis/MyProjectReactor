package com.github.kaydunovdenis.springsupplier.service;

import com.github.kaydunovdenis.springsupplier.Application;
import com.github.kaydunovdenis.springsupplier.entity.Address;
import com.github.kaydunovdenis.springsupplier.entity.Order;
import com.github.kaydunovdenis.springsupplier.entity.Recipient;
import com.github.kaydunovdenis.springsupplier.entity.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Следующие две аннотации делают наши тесты независимыми
 * т.е. перед каждым тестом запускается скрипт data.sql для наполнения базы тестовыми данными
 * <p>
 * DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
 * The presence of this annotation on a test method indicates that the underlying Spring container is 'dirtied'
 * during the execution of the test method, and thus must be rebuilt after the test method finishes execution
 * (regardless of whether the test passed or not).
 *
 * AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
 */
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class SupplierServiceTest {

    @Autowired
    private SupplierService supplierService;

    @Test
    void create() {
        //Before
        int expectedCountSuppliers = 5;

        Supplier supplier = getInstanceSupplier();
        supplier.setId(5L);
        Address address = supplier.getAddress();
        address.setId(5L);
        supplier.setAddress(address);
        //Then
        supplierService.create(supplier);
        //Given
        assertEquals(expectedCountSuppliers, supplierService.readAll().size());
    }

    @Test
    void delete() {
        //Before
        Long idSupplier = 4L;
        int expectedCountSuppliers = 3;
        Assertions.assertNotNull(supplierService.read(idSupplier));
        //Then
        supplierService.delete(idSupplier);
        //Given
        int actualCountSuppliers = supplierService.readAll().size();
        //Given
        assertEquals(expectedCountSuppliers, actualCountSuppliers);
    }

    @Test
    void update() {
        //Before
        Supplier expectedSupplier = getInstanceSupplier();
        expectedSupplier.setName("OtherName");
        //Then
        Supplier actualSupplier = supplierService.update(expectedSupplier);
        //Given
        assertEquals(expectedSupplier, actualSupplier);
    }

    @Test
    @Transactional
        //TODO Transactional  is norm? потому что сравниваем два суплаера по полю ресипиентов, а там прокси
    void read() {
        //Before
        Long supplierID = 1L;
        Supplier expectedSupplier = getInstanceSupplier();
        //Then
        Supplier actualSupplier = supplierService.read(supplierID);
        //Given
        assertEquals(expectedSupplier, actualSupplier);
    }

    @Test
    void checkEntityNotFoundException() {
        //Before
        Long idSupplier = 4L;
        Assertions.assertNotNull(supplierService.read(idSupplier));
        //Then
        supplierService.delete(idSupplier);
        //Given
        Assertions.assertThrows(ResponseStatusException.class, () -> supplierService.read(idSupplier));
    }

    @Test
    void readAll() {
        //Before
        int expectedCountSuppliers = 4;
        //Then
        List<Supplier> suppliers = supplierService.readAll();
        //Given
        assertEquals(expectedCountSuppliers, suppliers.size());
    }

    private Supplier getInstanceSupplier() {
        Address address = new Address("Belarus", "Gomel", "Covetskaya", 32);
        address.setId(1L);
        List<Order> orders = new ArrayList<>();
        Set<Recipient> recipients = new HashSet<>();

        Supplier expectedSupplier = new Supplier("Supplier1", address);
        expectedSupplier.setId(1L);
        expectedSupplier.setOrders(orders);
        expectedSupplier.setRecipients(recipients);
        return expectedSupplier;
    }
}