package com.github.kaydunovdenis.springsupplier.repository;

import com.github.kaydunovdenis.springsupplier.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Chose JpaRepository instead CrudRepository, because
 * method findAll from JpaRepository is return List but not Iterable
 */
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    /**
     * Кастомный метод
     * @param name - Supplier's name
     * @return Supplier or null if Supplier not found
     */
    List<Supplier> findByName(String name);

    /**
     * Чтобы решить вопрос с транзакциями использован Query-HQL
     * запрос решает проблему Query N+1
     * Другим способом решения этой проблемы может быть использован entity graph
     *
     * @param id supplier
     * @return Supplier with all depends entities
     */
    @Query(value = "select s from Supplier s " +
            "left join fetch s.orders o " +
            "left join fetch o.products " +
            "left join fetch s.recipients " +
            "where s.id =:id ")
    Supplier readExpandSupplier(@Param("id") Long id);
}
