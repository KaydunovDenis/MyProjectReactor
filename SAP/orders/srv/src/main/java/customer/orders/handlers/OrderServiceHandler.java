package customer.orders.handlers;

import cds.gen.orders.Orders;
import cds.gen.orderservice.OrderService_;
import com.sap.cds.services.cds.CdsService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.ServiceName;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@ServiceName(OrderService_.CDS_NAME)
public class OrderServiceHandler implements EventHandler {

	@After(event = CdsService.EVENT_READ)
	public void discountBooks(Stream<Orders> orders) {
		orders.filter(b -> b.getPrice() != null)
		.filter(b -> b.getPrice() > 2000)
		.forEach(b -> b.setDescription(b.getDescription() + " (Good oder!!!)"));
	}

}