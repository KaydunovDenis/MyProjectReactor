package com.github.kaydunovdenis.springsupplier.service;

import com.github.kaydunovdenis.springsupplier.Application;
import com.github.kaydunovdenis.springsupplier.SupplierProvider;
import com.github.kaydunovdenis.springsupplier.entity.Supplier;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    @DisplayName("When add new Supplier, count of suppliers increment to 1 ")
    void save1() {
        //Before
        int expectedCountOfSuppliers = getCountOfSuppliers() + 1;
        Supplier supplier = SupplierProvider.createSupplier();
        //Then
        supplierService.save(supplier);
        //Given
        assertEquals(expectedCountOfSuppliers, getCountOfSuppliers());
    }

    @Test
    @DisplayName("When add new Supplier, receive supplier equal similar Supplier ")
    void save2() {
        //Before
        Supplier expectedSupplier = SupplierProvider.createSupplier();
        //Then
        Supplier actualSupplier = supplierService.save(expectedSupplier);
        //Given
        assertEquals(expectedSupplier, actualSupplier);
    }

    @Test
    @DisplayName("When read Supplier by Name, I receive Supplier with this name")
    void readByName() {
        //Before
        int expectedCountSuppliers = 1;
        String name = "Supplier1";
        //Then
        List<Supplier> suppliers = supplierService.readByName(name);
        //Given
        assertEquals(expectedCountSuppliers, suppliers.size());
        suppliers.forEach(supplier -> assertEquals(name, supplier.getName()));
    }

    @Test
    @DisplayName("When update Supplier which not found in DB, then empty save Supplier")
    void update1() {
        //Before
        Supplier expectedSupplier = SupplierProvider.createSupplier();
        //Then
        Supplier actualSupplier = supplierService.update(expectedSupplier);
        //Given
        assertEquals(expectedSupplier, actualSupplier);
    }

    @Test
    @DisplayName("When update Supplier with new Name, then save differences and ID did not change")
    void update2() {
        //Before
        final String NAME = "New name for Update";
        Supplier expectedSupplier = supplierService.save(SupplierProvider.createSupplier());
        //Then
        expectedSupplier.setName(NAME);
        Supplier actualSupplier = supplierService.update(expectedSupplier);
        //Given
        assertEquals(expectedSupplier.getName(), actualSupplier.getName());
        assertEquals(expectedSupplier.getId(), actualSupplier.getId());
    }

    @Test
    @DisplayName("When delete Supplier, count of supplier decreases by 1")
    void delete1() {
        //Before
        final Long idSupplier = 4L;
        int expectedCountSuppliers = getCountOfSuppliers() - 1;
        //Then
        supplierService.delete(idSupplier);
        //Given
        int actualCountSuppliers = getCountOfSuppliers();
        //Given
        assertEquals(expectedCountSuppliers, actualCountSuppliers);
    }

    @Test
    void readExpandSupplier() {
        //TODO write test for readExpandSupplier
        //Before
        Supplier expectedSupplier = supplierService.save(SupplierProvider.createSupplier());
        //Then
        Supplier actualSupplier = supplierService.readExpandSupplier(1L);
        //Given
        assertNotNull(actualSupplier);
//        assertEquals(expectedSupplier.getId(), actualSupplier.getId());
//        assertEquals(expectedSupplier.getName(), actualSupplier.getName());
////        assertEquals(expectedSupplier.getAddress(), actualSupplier.getAddress());
////        assertEquals(expectedSupplier.getRecipients(), actualSupplier.getRecipients());
//        assertEquals(expectedSupplier.getOrders(), actualSupplier.getOrders());
//        assertEquals(expectedSupplier.getOrders(), actualSupplier.getOrders());

    }

    @Test
    void findAll() {
        //Before
        final int expectedCountSuppliers = 4;
        //Then
        List<Supplier> suppliers = supplierService.findAll();
        //Given
        assertEquals(expectedCountSuppliers, suppliers.size());
    }

    @Test
    @DisplayName("When id is null then receive NullPointerException")
    void findById1() {
        //Before
        Supplier expectedSupplier = SupplierProvider.createSupplier();
        Long supplierId = expectedSupplier.getId();
        //Then
        //Given
        assertThrows(NullPointerException.class, () -> supplierService.findById(supplierId));
    }

    @Test
    @DisplayName("When not found Supplier by Id then receive exception HttpStatus.NOT_FOUND")
    void findById2() {
        //Before
        final Long missingSupplierId = 5L;
        //Then
        //Given
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> supplierService.findById(missingSupplierId));
        assertEquals(HttpStatus.NOT_FOUND.toString(), exception.getMessage());
    }

    @Test
    @DisplayName("When supplier with id exist then receive supplier by id")
    void findById3() {
        //Before
        Long supplierId = 2L;
        //Then
        Supplier actualSupplier = supplierService.findById(supplierId);
        //Given
        assertNotNull(actualSupplier);
    }

    @Test
    @DisplayName("When save supplier then receive equal supplier")
    void saveAndThenFindById() {
        //Before
        Supplier expectedSupplier = supplierService.save(SupplierProvider.createSupplier());
        //Then
        Supplier actualSupplier = supplierService.findById(expectedSupplier.getId());
        //Given
        assertEquals(expectedSupplier.getId(), actualSupplier.getId());
        assertEquals(expectedSupplier.getAddress(), actualSupplier.getAddress());
        assertEquals(expectedSupplier.getName(), actualSupplier.getName());
    }

    @Test
    @DisplayName("When receive supplier, it don't have Recipients and Orders")
    void lazyInitializationTest() {
        //Before
        Supplier expectedSupplier = supplierService.save(SupplierProvider.createSupplier());
        //Then
        Supplier actualSupplier = supplierService.findById(expectedSupplier.getId());
        //Given
        assertThrows(LazyInitializationException.class, () -> expectedSupplier.equals(actualSupplier));
    }

    private int getCountOfSuppliers() {
        return supplierService.findAll().size();
    }
}