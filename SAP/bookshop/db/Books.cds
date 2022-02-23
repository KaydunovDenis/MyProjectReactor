namespace my.bookshop;

//Need for import entity Currency from SAP library
using {Currency} from '@sap/cds/common';

//instead of "key id: Integer;"
//CAP will automatically generate a CUID and insert it
//into this field whenever you create a new entity.
using {cuid} from '@sap/cds/common';

//add ":cuid" for import it
entity Books: cuid {
//    key id : Integer;
    title  : bookshop.Name;
    descr  : bookshop.Text;
    author : bookshop.Name;
    genre  : bookshop.Text;
    rating : Decimal(2, 1) @assert.range : [ 0.0, 5.0 ];
    price  : Decimal(9,2);
    currency : Currency;
    reviews  : Association to many bookshop.Reviews on reviews.book = $self;
}
