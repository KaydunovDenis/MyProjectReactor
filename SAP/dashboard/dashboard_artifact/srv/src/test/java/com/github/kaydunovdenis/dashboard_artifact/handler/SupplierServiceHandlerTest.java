package com.github.kaydunovdenis.dashboard_artifact.handler;


import cds.gen.suppliersservice.Suppliers;
import com.sap.cds.impl.ResultImpl;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.cds.CqnService;

import org.junit.Ignore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class SupplierServiceHandlerTest {

    @InjectMocks
    private SupplierServiceHandler handler;

    @Mock
    private CqnService service;

    @Ignore
    @Test
    public void getOrders() {

    }

    @Test
    @DisplayName("getSuppliersExs")
    public void getSuppliersExs() {//Given
        //Given
        List<Suppliers> suppliers = new ArrayList<>();
        suppliers.add(newSupplier(1));

        //Where
        when(service.run(any(CqnSelect.class)))
                .thenReturn(new ResultImpl().result());

        handler.getSuppliersExs(suppliers);
        //Then
        suppliers.forEach(supplier -> {
            supplier.getOrders().forEach((order ->
                    assertEquals(supplier.getSupplierId(), order.getSupplierID())));
        });

    }

    @Test
    @DisplayName("checkTheNumberOfExecutions")
    public void getSuppliersExsTestOfExecutions() {

        verify(service, times(1)).run(any(CqnSelect.class));

    }


    private Suppliers newSupplier(Integer id) {
        Suppliers supplier = Suppliers.create();
        supplier.setSupplierId(id);
        return supplier;
    }
}