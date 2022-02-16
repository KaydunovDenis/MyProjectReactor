using dashboardeleven from '../db/schema';

service OwnerService  @(requires: ['admin']){
    entity Suppliers as projection on dashboardeleven.Suppliers;
    entity Addresses as projection on dashboardeleven.Addresses;
};

service CustomerService @(requires: ['admin', 'user']) {
    @readonly entity Suppliers as projection on dashboardeleven.Suppliers;
    @readonly entity Addresses as projection on dashboardeleven.Addresses;
};


