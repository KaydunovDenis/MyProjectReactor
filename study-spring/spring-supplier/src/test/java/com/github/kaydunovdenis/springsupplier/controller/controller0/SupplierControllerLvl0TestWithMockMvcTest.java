package com.github.kaydunovdenis.springsupplier.controller.controller0;

import com.github.kaydunovdenis.springsupplier.SupplierProvider;
import com.github.kaydunovdenis.springsupplier.entity.RequestBody;
import com.github.kaydunovdenis.springsupplier.entity.Supplier;
import com.github.kaydunovdenis.springsupplier.service.SupplierService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//TODO написать тесты согласно туториалу по ссылке
/**
 * https://www.baeldung.com/spring-boot-testing#unit-testing-with-webmvctest
 */
//TODO Replace  RunWith for JUnit 5
@RunWith(SpringRunner.class)
@WebMvcTest(SupplierControllerLvl0.class)
class SupplierControllerLvl0TestWithMockMvcTest extends SupplierProvider {

    private static final String URI = "/controller0/supplier";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SupplierService service;

    @Disabled("no comment")
    @Test
    @DisplayName("givenSuppliers_whenGetSuppliers_thenReturnJsonArray")
    void process() throws Exception {
        Supplier supplier = createSupplier();
        List<Supplier> suppliers = List.of(supplier);
        RequestBody requestBody = new RequestBody(HttpMethod.GET, supplier);

        given(service.findAll()).willReturn(suppliers);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(supplier.getName())));
    }

    @Disabled("no comment")
    @Test
    @DisplayName("givenSuppliers_whenGetSuppliers_thenReturnJsonArray")
    void checkErrorWhenBodyIsNull() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URI))
                .andExpect(status().isBadRequest());
    }


}