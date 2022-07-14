package com.github.kaydunovdenis.springsupplier.repository;

import com.github.kaydunovdenis.springsupplier.entity.Supplier;
import com.sun.istack.NotNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    /**
     * Кастомный метод
     *
     * @param name - Supplier's name
     * @return Supplier or null if Supplier not found
     */
    List<Supplier> findByName(String name);


    /**
     * Переопределяем метод из репозитория JpaRepository, чтобы решить вопрос с транзакциями
     * Query - HQL запрос с использованием fetch решает проблему Query N+1
     * Другим способом решения этой проблемы может быть использован entity graph
     *
     * @param id supplier
     * @return object Supplier. class
     */
    //TODO write good query
//    @Query("FROM Supplier supplier " +
////            "join fetch supplier.address address " +
////            "join sr on suppliers.id = sr.supplier_id " +
//            "left join supplier.recipients on supplier.id = recipients.id " +
//            "WHERE supplier.id = :id "
//    )
    default Supplier readExpandSupplier(@Param("id") Long id) {
        final String DB_URL = "jdbc:postgresql://localhost:5432/supplier";
        final String USER = "postgres";
        final String PASS = "password";
        final String QUERY = "SELECT * FROM suppliers " +
                "WHERE suppliers.id = " + id;

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);
        ) {
            while(rs.next()){
                //Display values
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", Age: " + rs.getInt("age"));
                System.out.print(", First: " + rs.getString("first"));
                System.out.println(", Last: " + rs.getString("last"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Supplier supplier = null;
        return supplier;
    };
}
