package com.github.kaydunovdenis.springsupplier.repository;

import com.github.kaydunovdenis.springsupplier.Application;
import com.github.kaydunovdenis.springsupplier.entity.Address;
import com.github.kaydunovdenis.springsupplier.entity.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
class SupplierRepositoryTest {

    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(supplierRepository).isNotNull();
    }

    @Test
    void saveTest() {
        Address address = new Address("Belarus", "Gomel", "Covetskaya", 32);
        Supplier supplier = new Supplier("Andreas", address);

        supplierRepository.save(supplier);
        Assertions.assertEquals(supplierRepository.count(), 1);
    }

    @Test
    void should_find_all_users_is_empty() {
        Iterable<Supplier> users = supplierRepository.findAll();
        assertThat(users).isEmpty();
    }

    @Test
    @Sql("createSupplier.sql")
    void should_find_all_users() {
        Iterable<Supplier> users = supplierRepository.findAll();
        assertThat(users).hasSize(1);
    }

    @Test
    @Sql("createSupplier.sql")
    void should_delete_user() {
        //Given
        Optional<Supplier> supplier = supplierRepository.findById(1L);
        assertTrue(supplier.isPresent());

        //When
        supplierRepository.delete(supplier.get());

        //Then
        Iterable<Supplier> users = supplierRepository.findAll();
        assertThat(users).isEmpty();
    }
}


