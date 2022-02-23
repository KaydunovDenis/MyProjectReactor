namespace my.bookshop;

entity Books {
key ID : Integer;
title  : String;
stock  : Integer;
}

entity Toys {
key ID       : Integer;
description  : String;
}

entity User {
   key ID : Integer;
   name : String;
   }