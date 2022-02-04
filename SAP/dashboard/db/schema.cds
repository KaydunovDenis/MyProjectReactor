namespace dashboard;

entity Suppliers {
   key SupplierID  :  Integer;
   Name  :  String;
   Description  :  String;
   Address  :  Association to Addresses;
}

entity Addresses {
   key AddressID  :  Integer;
   Country  :  String;
   City  :  String;
   Street  :  String;
   House  :  Integer;
   Apartment: Integer;
   Suppliers  : Association to many Suppliers
                on Suppliers.Address = $self;
}
