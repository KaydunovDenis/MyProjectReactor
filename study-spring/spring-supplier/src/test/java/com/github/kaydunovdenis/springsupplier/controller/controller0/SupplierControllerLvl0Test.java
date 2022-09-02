package com.github.kaydunovdenis.springsupplier.controller.controller0;

import com.github.kaydunovdenis.springsupplier.SupplierProvider;
import com.github.kaydunovdenis.springsupplier.entity.RequestBody;
import com.github.kaydunovdenis.springsupplier.entity.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * use of webEnvironment=RANDOM_PORT
 * to start the server with a random port (useful to avoid conflicts in test environments
 *
 * Следующие две аннотации делают наши тесты независимыми
 *  * т.е. перед каждым тестом запускается скрипт data.sql для наполнения базы тестовыми данными
 *  * <p>
 *  * DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
 *  * The presence of this annotation on a test method indicates that the underlying Spring container is 'dirtied'
 *  * during the execution of the test method, and thus must be rebuilt after the test method finishes execution
 *  * (regardless of whether the test passed or not).
 *  * <p>
 *  * AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class SupplierControllerLvl0Test extends SupplierProvider {

    @Autowired
    SupplierControllerLvl0 supplierControllerLvl0;

    @Test
    @DisplayName("When post the supplier, then receive the supplier equals to sent")
    void handle1() {
        //Given
        Supplier expectedSupplier = createSupplier();
        RequestBody requestBodyPost = new RequestBody(HttpMethod.POST, expectedSupplier);
        supplierControllerLvl0.request(requestBodyPost);
        //When
        RequestBody requestBodyGet = new RequestBody(HttpMethod.GET, null);
        ArrayList<Supplier> receiveSuppliers = (ArrayList<Supplier>) supplierControllerLvl0.request(requestBodyGet);
        Supplier actualSupplier = receiveSuppliers.get(0);
        assertEquals(expectedSupplier, actualSupplier);

    }
}