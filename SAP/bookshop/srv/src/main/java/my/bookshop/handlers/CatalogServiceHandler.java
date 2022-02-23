package my.bookshop.handlers;

import cds.gen.catalogservice.AddReviewContext;
import cds.gen.catalogservice.CatalogService_;
import cds.gen.catalogservice.Reviews;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import org.springframework.stereotype.Component;

@Component
@ServiceName(CatalogService_.CDS_NAME)
public class CatalogServiceHandler implements EventHandler {

    @On(event = AddReviewContext.CDS_NAME)
    public void addReview(AddReviewContext context) {
        System.out.println(context.getCqn());

        Reviews reviews = Reviews.create();
        reviews.setTitle(context.getTitle());
        reviews.setRating(context.getRating());
        reviews.setText(context.getText());

        context.setCompleted();
    }
}
