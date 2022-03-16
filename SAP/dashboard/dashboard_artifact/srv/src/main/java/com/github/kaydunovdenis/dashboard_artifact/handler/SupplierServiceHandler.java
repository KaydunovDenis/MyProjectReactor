package com.github.kaydunovdenis.dashboard_artifact.handler;

import cds.gen.ordersservice.OrdersService_;
import cds.gen.suppliersservice.MyOrders;
import cds.gen.suppliersservice.MyOrders_;
import cds.gen.suppliersservice.Suppliers;
import cds.gen.suppliersservice.SuppliersService_;
import cds.gen.suppliersservice.Suppliers_;
import com.sap.cds.Result;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.cds.CdsReadEventContext;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@ServiceName(SuppliersService_.CDS_NAME)
public class SupplierServiceHandler implements EventHandler {

    @Autowired
    @Qualifier(OrdersService_.CDS_NAME)
    private CqnService orderService;

    @On(entity = MyOrders_.CDS_NAME)
    public Result getOrders(CdsReadEventContext context) {
        return orderService.run(context.getCqn());
    }

    @After(event = CqnService.EVENT_READ, entity = Suppliers_.CDS_NAME)
    public List<Suppliers> getSuppliersExs(List<Suppliers> suppliers) {
        if (!Objects.isNull(suppliers) && !suppliers.isEmpty()) {
            List<Integer> suppliersIds = suppliers.stream()
                    .map(Suppliers::getSupplierId)
                    .collect(Collectors.toList());

            CqnSelect select = Select.from(MyOrders_.class)
                    .where(a -> a.supplierID().in(suppliersIds));

            Result result = orderService.run(select);
            List<MyOrders> myOrdersList = result.listOf(MyOrders.class);

            Map<Integer, List<MyOrders>> myOrderMap = myOrdersList.stream()
                    .collect(Collectors.groupingBy(MyOrders::getSupplierID));

            suppliers.forEach(s -> {
                if (!Objects.isNull(s.getOrders())) {
                    s.setOrders(myOrderMap.get(s.getSupplierId()));
                }
            });
        }
        return suppliers;
    }
}