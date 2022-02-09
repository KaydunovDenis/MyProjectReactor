using capire2 from '../db/schema';

service OwnerService @(requires: 'Admin') {
    entity Suppliers as projection on capire2.Suppliers;
    entity Addresses as projection on capire2.Addresses;
}

service CustomerService @(requires: 'Admin', 'User') {
    @readonly entity Suppliers as projection on capire2.Suppliers;
    @readonly entity Addresses as projection on capire2.Addresses;
}




