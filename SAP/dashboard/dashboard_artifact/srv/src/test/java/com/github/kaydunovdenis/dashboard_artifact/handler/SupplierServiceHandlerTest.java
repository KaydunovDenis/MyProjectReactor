package com.github.kaydunovdenis.dashboard_artifact.handler;


import cds.gen.suppliersservice.MyOrders;
import cds.gen.suppliersservice.Suppliers;
import com.sap.cds.Result;
import com.sap.cds.impl.ResultImpl;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.cds.CqnService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SupplierServiceHandlerTest {

    @InjectMocks
    private SupplierServiceHandler handler;

    @Mock
    private CqnService service;

    @Mock
    private Result result;

    @Test
    @DisplayName("Check that Service doesn't connect to DB if there aren't suppliers")
    void testGetSuppliersExsCheckThatServiceDoesNotConnectToDb() {
        //Given
        List<Suppliers> suppliers = new ArrayList<>();
        //When
        handler.getSuppliersExs(suppliers);
        //Then
        verify(service, never()).run(any(CqnSelect.class));
    }

    @Test
    @DisplayName("Check what this method invokes a request to DB only once")
    void testGetSuppliersExsCheckNumberOfConnectToDb() {
        setUp();
        //Then
        verify(service).run(any(CqnSelect.class));
    }

    @Test
    @DisplayName("Checking that Orders have been added to Suppliers")
    void testGetSuppliersExsCheckExtensionOfSuppliersByOrders() {
        setUp().stream()
                .filter(s -> !Objects.isNull(s.getOrders()))
                .forEach(s -> s.getOrders().forEach(o -> assertEquals(s.getSupplierId(), o.getSupplierID())));
    }

    @Test
    @DisplayName("Checking that Orders haven't been added to Supplier")
    void testGetSuppliersExsCheckSupplierWithoutOrders() {
        int supplierIdWithoutOrders = 3;
        setUp().stream()
                .filter(s -> s.getSupplierId() == supplierIdWithoutOrders)
                .findAny()
                .ifPresent(s -> assertNull(s.getOrders()));
    }

    private List<Suppliers> setUp() {
        //Given
        List<Suppliers> suppliers = Arrays.asList(
                mySupplier(1),
                mySupplier(2),
                mySupplier(3));
        List<MyOrders> orders = Arrays.asList(
                myOrder(1),
                myOrder(2),
                myOrder(4),
                myOrder(1)
        );

        //When
        when(service.run(any(CqnSelect.class))).thenReturn(result);
        when(result.listOf(MyOrders.class)).thenReturn(orders);

        handler.getSuppliersExs(suppliers);
        return suppliers;
    }

    private MyOrders myOrder(Integer supplierId) {
        MyOrders myOrders = MyOrders.create();
        myOrders.setSupplierID(supplierId);
        return myOrders;
    }

    private Suppliers mySupplier(Integer id) {
        Suppliers supplier = Suppliers.create();
        supplier.setSupplierId(id);
        return supplier;
    }
}