package com.github.kaydunovdenis;

import com.github.kaydunovdenis.entity.Role;
import com.github.kaydunovdenis.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) {
//        BlockingDeque<Connection> pool = null;
//        Connection connection = pool.take();
//        SessionFactory

//        Connection connection = DriverManager
//                .getConnection("db.url", "db.username", "db.password");
//        Session
        Configuration configuration = new Configuration();
        //Говорим Hibernate отслеживать сущность
        //перенесено в секцию маппинг в конфигурационном файле Hibernate
        //example  configuration.addAnnotatedClass(User.class)

        //Эта конфигурация позволяет осуществлять маппинг от thisName to this_name
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            System.out.println("OK");

            User user = User.builder()
                    .username("Iivan@gmail.com")
                    .firstname("Ivan")
                    .lastname("Ivanov")
                    .birthDate(LocalDate.of(2020, 1, 23))
                    .age(20)
                    .role(Role.ADMIN)
                    .build();

            session.save(user);
            session.getTransaction().commit();
        }
    }
}
