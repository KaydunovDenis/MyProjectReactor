package customer.helloworld.handlers;

import cds.gen.say.HelloContext;
import cds.gen.say.Say_;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import org.springframework.stereotype.Component;

@Component
@ServiceName(Say_.CDS_NAME)
public class SayHandler implements EventHandler {

    @On(event = HelloContext.CDS_NAME)
    public void sayHello(HelloContext context) {
        String name = context.getTo();
        String greeting = "Hello " + name + "!";
        context.setResult(greeting);
    }
}
