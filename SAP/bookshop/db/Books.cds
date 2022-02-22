namespace my.bookshop;

//Need for import entity Currency from SAP library
using {Currency} from '@sap/cds/common';

//instead of "key id: Integer;"
//CAP will automatically generate a CUID and insert it
//into this field whenever you create a new entity.
using {cuid} from '@sap/cds/common';
using {my.bookshop as bookshop} from './index';

//add ":cuid" for import it
entity Books: cuid {
//    key id : Integer;
    title  : String(100);
    descr  : String(1000);
    author : String(100);
    genre  : String(100);
    rating : Decimal(2, 1) @assert.range : [ 0.0, 5.0 ];
    price  : Decimal(9,2);
    currency : Currency;
    reviews  : Association to many bookshop.Reviews on reviews.book = $self;
}
