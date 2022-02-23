package my.bookshop.handlers;

import cds.gen.catalogservice.AddReviewContext;
import cds.gen.catalogservice.Books;
import cds.gen.catalogservice.CatalogService_;
import cds.gen.catalogservice.Reviews;
import com.sap.cds.ql.cqn.AnalysisResult;
import com.sap.cds.ql.cqn.CqnAnalyzer;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.reflect.CdsModel;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ServiceName(CatalogService_.CDS_NAME)
public class CatalogServiceHandler implements EventHandler {

    private final CqnAnalyzer analyzer;

    public CatalogServiceHandler(CdsModel model) {
        this.analyzer = CqnAnalyzer.create(model);
    }

    @On(event = AddReviewContext.CDS_NAME)
    public void addReview(AddReviewContext context) {
        CqnSelect select = context.getCqn();
        AnalysisResult analysisResult = analyzer.analyze(select);
        Map<String, Object> targetKeys = analysisResult.targetKeys();
        String bookId = (String) targetKeys.get(Books.ID);
        String author = (String) targetKeys.get(Books.AUTHOR);
        String rating = (String) targetKeys.get(Books.RATING);


        System.out.println(context.getCqn());
        System.out.println(analysisResult);
        System.out.println(targetKeys);
        System.out.println("BookId: " + bookId);
        System.out.println("Author: " + author);
        System.out.println("Rating: " + rating);

        Reviews reviews = Reviews.create();
        reviews.setBookId(bookId);
        reviews.setTitle(context.getTitle());
        reviews.setRating(context.getRating());
        reviews.setText(context.getText());

        context.setCompleted();
    }
}
