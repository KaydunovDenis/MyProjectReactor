namespace my.bookshop;

//?
//instead of "key id: Integer;"
//CAP will automatically generate a CUID and insert it
//into this field whenever you create a new entity.
//which user created a review and when? Luckily, CAP provides us with a built-in,
//automatic feature for this too: the managed aspect.
using {cuid, managed} from '@sap/cds/common';

//add ":cuid" for import it
entity Reviews : cuid, managed {
    book   : Association to bookshop.Books;
    rating : Rating          @assert.range;
    title  : bookshop.Name   @mandatory;
    text   : bookshop.Text   @mandatory;
}

type Rating : Integer enum {
    Great    = 5;
    Good     = 4;
    Average  = 3;
    Poor     = 2;
    Bad      = 1;
    Terrible = 0;
}