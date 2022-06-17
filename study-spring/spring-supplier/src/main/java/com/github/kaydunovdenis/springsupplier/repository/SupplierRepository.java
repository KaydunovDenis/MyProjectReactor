package com.github.kaydunovdenis.springsupplier.repository;

import com.github.kaydunovdenis.springsupplier.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    /**
     * Это кастомный метод
     * @param name
     * @return Supplier or null if Supplier not found
     */
    Optional<Supplier> findByName(String name);
}
