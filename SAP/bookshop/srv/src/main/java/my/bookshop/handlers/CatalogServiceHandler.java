package my.bookshop.handlers;

import cds.gen.catalogservice.AddReviewContext;
import cds.gen.catalogservice.Books;
import cds.gen.catalogservice.CatalogService_;
import cds.gen.catalogservice.Reviews;
import cds.gen.my.bookshop.Reviews_;
import com.sap.cds.Result;
import com.sap.cds.ql.Insert;
import com.sap.cds.ql.cqn.AnalysisResult;
import com.sap.cds.ql.cqn.CqnAnalyzer;
import com.sap.cds.ql.cqn.CqnInsert;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.reflect.CdsModel;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.persistence.PersistenceService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ServiceName(CatalogService_.CDS_NAME)
public class CatalogServiceHandler implements EventHandler {

    private final PersistenceService db;
    private final CqnAnalyzer analyzer;

    public CatalogServiceHandler(PersistenceService db, CdsModel model) {
        this.db = db;
        this.analyzer = CqnAnalyzer.create(model);
    }

    /**
     *  The basic backend logic for our Add Review action.
     * @param context
     */
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

        Reviews review = Reviews.create();
        review.setBookId(bookId);
        review.setTitle(context.getTitle());
        review.setRating(context.getRating());
        review.setText(context.getText());

        //To execute any kind of operation on the database,
        // we use the persistence service method run.
        // The input to run is a CQN query generated by one of several of CAP’s
        // query builders, not surprisingly named Delete, Insert, Select, Upsert,
        // and Update. We use Insert here because we are
        // inserting a new entry into the database.
        CqnInsert reviewInsert = Insert.into(Reviews_.CDS_NAME).entry(review);
        System.out.println(reviewInsert);
        Result savedReview = db.run(reviewInsert);
        Reviews newReview = savedReview.single(Reviews.class);
        context.setResult(newReview);
    }
}
