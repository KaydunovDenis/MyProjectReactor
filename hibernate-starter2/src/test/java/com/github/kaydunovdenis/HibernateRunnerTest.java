package com.github.kaydunovdenis;

import com.github.kaydunovdenis.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.joining;

class HibernateRunnerTest {

    @Test
    @DisplayName("This method show how Hibernate create sql query using Reflection API")
    void checkReflectionAPI() throws SQLException, IllegalAccessException {
        User user = User.builder()
                .username("ivan@gmail.com")
                .firstname("Ivan")
                .lastname("Ivanov")
                .birthDate(LocalDate.of(2020, 1, 23))
                .age(20)
                .build();

        //Шаблон SQL запроса, в который мы подставим значения используя Reflection API и объект user
        String sql = """
                insert
                into
                %s
                (%s)
                values
                (%s)
                """;

        Class<? extends User> clazz = user.getClass();

        //проверяем наличие аннотации @Table, если её нет, то называем таблицу как имя класса
        String tableName = ofNullable(clazz.getAnnotation(Table.class))
                .map(tableAnnotation -> tableAnnotation.schema() + "." + tableAnnotation.name())
                .orElse(clazz.getName());

        //Достаем из класса поля и получаем список столбцов для таблицы
        Field[] declaredFields = clazz.getDeclaredFields();

        //проверяем наличие аннотации @Column, если её нет, то называем столбец как поле класса
        //тут могут применяться различные стратегии сопоставления имен
        //как Camel или через underline
        String columnNames = Arrays.stream(declaredFields)
                .map(field -> ofNullable(field.getAnnotation(Column.class))
                        .map(Column::name)
                        .orElse(field.getName()))
                .collect(joining(", "));

        String columnValues = Arrays.stream(declaredFields)
                .map(field -> "?")
                .collect(joining(", "));


        String preparedSql = sql.formatted(tableName, columnNames, columnValues);
        System.out.println(preparedSql);

//        Connection connection = null;
//        PreparedStatement preparedStatement = connection.prepareStatement(preparedSql);
//        for (Field declaredField : declaredFields) {
//            declaredField.setAccessible(true);
//            preparedStatement.setObject(1, declaredField.get(user));
//        }
    }

}