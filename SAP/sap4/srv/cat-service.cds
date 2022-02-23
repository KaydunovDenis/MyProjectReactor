using my.bookshop as my from '../db/data-model';

service CatalogService {
    @readonly entity Books as projection on my.Books;

    @readonly entity Toys as projection on my.Toys;

    @readonly entity User as projection on my.User;
}