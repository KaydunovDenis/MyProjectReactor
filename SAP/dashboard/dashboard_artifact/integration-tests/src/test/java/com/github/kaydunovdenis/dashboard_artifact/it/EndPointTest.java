package com.github.kaydunovdenis.dashboard_artifact.it;

import com.github.kaydunovdenis.dashboard_artifact.Application;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class EndPointTest {

	@Autowired
	private MockMvc mockMvc;

	@Disabled("See ticket which not exist))))")
	@Test
	void testReadBooks() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/odata/v4/SuppliersService/Suppliers"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.value[0].name").value(containsString("Belkis")))
				.andExpect(jsonPath("$.value[0].supplier_ID").value(1));
	}
}
