package com.github.kaydunovdenis.springsupplier.repository;

import com.github.kaydunovdenis.springsupplier.entity.Address;
import com.github.kaydunovdenis.springsupplier.entity.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
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
        final Address address = new Address("Belarus", "Gomel", "Covetskaya", 32);
        supplierRepository.save(new Supplier("Andreas", address));
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
        var supplier = supplierRepository.findById(1L);
        assertTrue(supplier.isPresent());

        //When
        supplierRepository.delete(supplier.orElseThrow());

        //Then
        Iterable<Supplier> users = supplierRepository.findAll();
        assertThat(users).isEmpty();
    }
}


