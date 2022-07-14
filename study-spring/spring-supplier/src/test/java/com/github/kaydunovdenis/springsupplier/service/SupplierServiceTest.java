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
    void save() {
        //Before
        int expectedCountSuppliers = 5;

        Supplier supplier = getInstanceSupplier();
        supplier.setId(5L);
        Address address = supplier.getAddress();
        address.setId(5L);
        supplier.setAddress(address);
        //Then
        supplierService.save(supplier);
        //Given
        assertEquals(expectedCountSuppliers, supplierService.readAll().size());
    }

    @Test
    void readByName() {
        //Before
        int expectedCountSuppliers = 1;
        String name = "Supplier1";
        //Then
        List<Supplier> suppliers = supplierService.readByName(name);
        //Given
        assertEquals(expectedCountSuppliers, suppliers.size());
        suppliers.forEach(supplier ->
                assertEquals(name, supplier.getName()));
    }

    @Test
    void update() {
        //Before
        Supplier expectedSupplier = getInstanceSupplier();
        expectedSupplier.setName("OtherName");
        //Then
        Supplier actualSupplier = supplierService.update(expectedSupplier, expectedSupplier.getId());
        //Given
        assertEquals(expectedSupplier, actualSupplier);
    }

    @Test
    void deleteById() {
        //Before
        Long idSupplier = 4L;
        int expectedCountSuppliers = 3;
        Assertions.assertNotNull(supplierService.readById(idSupplier));
        //Then
        supplierService.deleteById(idSupplier);
        //Given
        int actualCountSuppliers = supplierService.readAll().size();
        //Given
        assertEquals(expectedCountSuppliers, actualCountSuppliers);
    }

    @Test
    void readExpandSupplier() {
        //Before
        Supplier expectedSuppliers = getInstanceSupplier();
        //Then
        Supplier actualSupplier = supplierService.readExpandSupplier(expectedSuppliers.getId());
        //Given
        assertEquals(expectedSuppliers, actualSupplier);
        //TODO
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

    @Test
    void findById() {
        //Before
        Supplier expectedSupplier = getInstanceSupplier();
        Long supplierId = expectedSupplier.getId();
        //Then
        Supplier actualSupplier = supplierService.readById(supplierId);
        //Given
        assertEquals(expectedSupplier.getId(), actualSupplier.getId());
        assertEquals(expectedSupplier.getName(), actualSupplier.getName());
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