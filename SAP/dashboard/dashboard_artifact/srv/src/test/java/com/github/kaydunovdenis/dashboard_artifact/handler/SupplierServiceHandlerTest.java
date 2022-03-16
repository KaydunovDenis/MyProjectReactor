package com.github.kaydunovdenis.dashboard_artifact.handler;


import cds.gen.suppliersservice.MyOrders;
import cds.gen.suppliersservice.Suppliers;
import com.sap.cds.Result;
import com.sap.cds.impl.ResultImpl;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.cds.CqnService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class SupplierServiceHandlerTest {
    private List<Suppliers> suppliers;
    private List<MyOrders> orders;

    @InjectMocks
    private SupplierServiceHandler handler;

    @Mock
    private CqnService service;

    @Mock
    private Result result;

    @BeforeEach
    void fillingMockDatabase() {
        suppliers = new ArrayList<>();
        suppliers.add(newSupplier(1));
        suppliers.add(newSupplier(2));
        suppliers.add(newSupplier(3));

        orders = new ArrayList<>();
        MyOrders order1 = MyOrders.create();
        order1.setSupplierID(1);
        MyOrders order2 = MyOrders.create();
        order2.setSupplierID(1);
        MyOrders order3 = MyOrders.create();
        order3.setSupplierID(2);
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
    }

    @Test
    @DisplayName("Check what count of Suppliers before equal count of Suppliers after the execution this method")
    void testGetSuppliersExs_checkCountSuppliers() {
        //Given
        int sizeExpect = suppliers.size();

        //When
        when(service.run(any(CqnSelect.class))).thenReturn(new ResultImpl().result());
        handler.getSuppliersExs(suppliers);
        //then
        int sizeResult = suppliers.size();
        assertEquals(sizeExpect, sizeResult);
    }

    //TODO PArameterized test null or new ArrayList
    @Test
    @DisplayName("Check that Service don't connect to DB if there aren't suppliers")
    void testGetSuppliersExs_CheckThatServiceDoesNotConnectToDb() {
        //Given
        suppliers = null;
        //When
        handler.getSuppliersExs(suppliers);
        //then
        verify(service, times(0)).run(any(CqnSelect.class));
    }

    @Test
    @DisplayName("Check what Service connect to DB only once")
    void testGetSuppliersExs_CheckNumberOfConnectToDb() {
        //Given
        suppliers = new ArrayList<>();
        //When
//        when(service.run(any(CqnSelect.class))).thenReturn(new ResultImpl().result());
        handler.getSuppliersExs(suppliers);
        //then
        verify(service, times(0)).run(any(CqnSelect.class));
    }

    //TODO PArametarizedTEST
    @Test
    @DisplayName("Check what Orders has add to Suppliers")
    void testGetSuppliersExs_CheckExtensionOfSuppliersByOrders() {
        //Given
        int idSupplier = suppliers.size() + 1;
        suppliers.add(newSupplier(idSupplier));
        MyOrders orderN = MyOrders.create();
        orderN.setSupplierID(idSupplier);
        orders.add(orderN);

        //When
        when(service.run(any(CqnSelect.class))).thenReturn(result);
        when(result.listOf(MyOrders.class)).thenReturn(orders);

        handler.getSuppliersExs(suppliers);
        //Then
        suppliers.forEach(supplier -> {
            if (!Objects.isNull(supplier.getOrders())) {
                supplier.getOrders().forEach((order ->
                        assertEquals(supplier.getSupplierId(), order.getSupplierID())));
            }
        });
    }

    private Suppliers newSupplier(Integer id) {
        Suppliers supplier = Suppliers.create();
        supplier.setSupplierId(id);
        return supplier;
    }
}