namespace dashboardeleven;

//using OrderService.Orders;

entity Suppliers {
   key supplierID  :  Integer;
   name  :  String(50) not null;
   description  :  String(300);
   address  :  Association to Addresses;
//   orders: Association many to OrderService.Orders;
}

entity Addresses {
   key addressID  :  Integer;
   country  :  String(50);
   city  :  String(50);
   street  :  String(50);
   house  :  Integer;
   apartment: Integer;
   suppliers  : Association to many Suppliers
                on suppliers.address = $self;
}