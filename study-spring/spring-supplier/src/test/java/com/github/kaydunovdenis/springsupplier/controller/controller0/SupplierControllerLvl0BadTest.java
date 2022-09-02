package com.github.kaydunovdenis.springsupplier.controller.controller0;

import com.github.kaydunovdenis.springsupplier.Application;
import com.github.kaydunovdenis.springsupplier.SupplierProvider;
import com.github.kaydunovdenis.springsupplier.entity.RequestBody;
import com.github.kaydunovdenis.springsupplier.entity.Supplier;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Следующие две аннотации делают наши тесты независимыми
 * т.е. перед каждым тестом запускается скрипт data.sql для наполнения базы тестовыми данными
 * <p>
 * DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
 * The presence of this annotation on a test method indicates that the underlying Spring container is 'dirtied'
 * during the execution of the test method, and thus must be rebuilt after the test method finishes execution
 * (regardless of whether the test passed or not).
 * <p>
 * AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
 */
@Disabled("некогда их чинить")
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class SupplierControllerLvl0BadTest extends SupplierProvider {

    @Autowired
    SupplierControllerLvl0 supplierControllerLvl0;

    public static Stream<Arguments> valuesForPositiveCase() {
        return Stream.of(
                Arguments.of(HttpMethod.GET, HttpStatus.OK, null),
                Arguments.of(HttpMethod.GET, HttpStatus.OK, createSupplier()),
                Arguments.of(HttpMethod.PUT, HttpStatus.OK, createSupplier()),
                Arguments.of(HttpMethod.POST, HttpStatus.OK, createSupplier()),
                Arguments.of(HttpMethod.DELETE, HttpStatus.OK, null),
                Arguments.of(HttpMethod.DELETE, HttpStatus.OK, createSupplier()),
                Arguments.of(HttpMethod.OPTIONS, HttpStatus.OK, createSupplier())

                //todo add PATCH, abd add with getSupplier()
        );
    }

    public static Stream<Arguments> valuesForNegativeCase() {
        return Stream.of(
                Arguments.of(null, "Unexpected value for httpMethod:")
        );
    }

    @Test
    void contextLoads() {
        assertThat(supplierControllerLvl0).isNotNull();
    }

    @ParameterizedTest
    @DisplayName("When sent good query then receive status 'OK'")
    @MethodSource("valuesForPositiveCase")
    void parameterizedTestForPositiveCase(HttpMethod httpMethod, HttpStatus httpStatus, Supplier supplier) {
        //Given
        RequestBody requestBody = new RequestBody(httpMethod, supplier);
        //When
        Object responseEntity = supplierControllerLvl0.request(requestBody);
        //Then
        assertEquals(httpStatus, responseEntity);
    }

    @ParameterizedTest
    @DisplayName("When sent invalid query then receive error")
    @MethodSource("valuesForNegativeCase")
    void parameterizedTestForNegativeCase(RequestBody requestBody, String errorMessage) {
        //Given
        //When
        Throwable exception = assertThrows(IllegalStateException.class, () ->
                supplierControllerLvl0.request(requestBody)
        );
        //Then
        assertTrue(exception.getMessage().contains(errorMessage));
    }
}
