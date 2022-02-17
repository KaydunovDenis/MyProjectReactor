using dashboardeleven from '../db/schema';

service OwnerService  @(requires:'admin'){
    entity Suppliers as projection on dashboardeleven.Suppliers;
    entity Addresses as projection on dashboardeleven.Addresses;
};

service CustomerService @(requires:'user') {
    entity Suppliers as projection on dashboardeleven.Suppliers;
    entity Addresses as projection on dashboardeleven.Addresses;
};


