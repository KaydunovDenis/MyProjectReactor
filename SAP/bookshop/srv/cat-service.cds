using {my.bookshop as bookshop} from '../db/books';

@path : 'browse'
service CatalogService {
  entity Books as projection on bookshop.Books
//run command:
//cds compile srv/cat-service.cds --to edmx
//see what sent to our Fiori Elements
//
//<Action Name="addReview" IsBound="true">
//        <Parameter Name="in" Type="CatalogService.Books"/>
//        <Parameter Name="rating" Type="Edm.Int32"/>
//        <Parameter Name="title" Type="Edm.String"/>
//        <Parameter Name="text" Type="Edm.String"/>
//        <ReturnType Type="CatalogService.Reviews"/>
//      </Action>
    actions{
      action addReview(rating: bookshop.Rating, title: bookshop.Name, text: bookshop.Text) returns Reviews;
    } ;
  entity Reviews as projection on bookshop.Reviews;
}