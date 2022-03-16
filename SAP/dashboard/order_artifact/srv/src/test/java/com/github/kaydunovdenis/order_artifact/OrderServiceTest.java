package com.github.kaydunovdenis.order_artifact;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@AutoConfigureMockMvc
class OrderServiceTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Ignore
    @Test
    public void testReadOrders() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/odata/v4/OrdersService/Orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value[0].Id").value(containsString("Wuthering Heights")))
                .andExpect(jsonPath("$.value[0].price").value(100))
                .andExpect(jsonPath("$.value[0].supplierId").value(containsString("Jane Eyre (discounted)")));
    }
}
