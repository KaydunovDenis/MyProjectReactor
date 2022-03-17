package com.github.kaydunovdenis.dashboard_artifact.handler;

import cds.gen.suppliersservice.MyOrders;
import cds.gen.suppliersservice.Suppliers;
import com.sap.cds.Result;
import com.sap.cds.impl.ResultImpl;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.cds.CqnService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SupplierServiceHandler.class)
@ExtendWith(MockitoExtension.class)
class EndPointTest {

	@MockBean
	private SupplierServiceHandler handler;

	@Mock
	private CqnService service;

	@Mock
	private Result result;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getSuppliersExsShouldReturnStatusOk() {
		//Given
		List<Suppliers> suppliers = createMySupplierList();
		//When
		when(service.run(any(CqnSelect.class))).thenReturn(new ResultImpl().result());
		handler.getSuppliersExs(suppliers);
		//Then
		//"/odata/v4/SuppliersService/Suppliers"
		mockMvc.perform(MockMvcRequestBuilders.get("/odata"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.value[0].name").value("Belkis"))
				.andExpect(jsonPath("$.value[0].supplier_ID").value(1));
	}

	private List<Suppliers> createMySupplierList() {
		return Arrays.asList(mySupplier(1), mySupplier(2));
	}

	private Suppliers mySupplier(Integer id) {
		Suppliers supplier = Suppliers.create();
		supplier.setSupplierId(id);
		return supplier;
	}
}
