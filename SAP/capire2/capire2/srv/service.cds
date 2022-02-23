using capire2 from '../db/schema';

service OwnerService  {
    entity Suppliers as projection on capire2.Suppliers;
    entity Addresses as projection on capire2.Addresses;
}

service CustomerService @(requires: ['admin', 'user']) {
    entity Suppliers as projection on capire2.Suppliers;
    entity Addresses as projection on capire2.Addresses;
}

//annotate OwnerService with @(requires: 'admin');


