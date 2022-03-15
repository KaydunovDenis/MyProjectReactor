package com.github.kaydunov.dashboardeleven.handler;

//import cds.gen.orderservice.OrderService_;
//import cds.gen.orderservice.Orders_;
//import cds.gen.supplierservice.SupplierService_;
import com.sap.cds.Result;
import com.sap.cds.services.cds.CdsReadEventContext;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component
//@ServiceName(SupplierService_.CDS_NAME)
public class SupplierServiceHandler /*implements EventHandler*/ {

//	@Autowired
//    @Qualifier(OrderService_.CDS_NAME)
//	CqnService remoteservice;
//
//	@On(entity = Orders_.CDS_NAME)
//	Result readSuppliers(CdsReadEventContext context) {
//		return remoteservice.run(context.getCqn());
//	}

//	@After(event = CdsService.EVENT_READ)
//	public List<Suppliers> getSuppliers(List<Suppliers> suppliers) {
//		suppliers.forEach(System.out::println);
//		System.out.println("getOrdersForSupplier!!!!!!!!!!!!!!!");
//
//		return suppliers;
//	}
}
