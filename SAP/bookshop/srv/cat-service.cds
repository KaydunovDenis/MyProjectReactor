using {my.bookshop as my} from '../db/index';

@path : 'browse'
service CatalogService {
  entity Books as projection on my.Books
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
      action addReview(rating: my.Rating, title: my.Name, text: my.Text) returns Reviews;
    } ;

  entity Reviews as projection on my.Reviews;
}