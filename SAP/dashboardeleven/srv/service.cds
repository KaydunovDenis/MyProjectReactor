using dashboardeleven from '../db/schema';
//using {OrderService as remoteservice } from '../srv/external/OrderService';

service SupplierService {
    entity Suppliers as projection on dashboardeleven.Suppliers;
//    entity Addresses as projection on dashboardeleven.Addresses;
};

//service CustomerService @(requires:'user') {
//    entity Suppliers as projection on dashboardeleven.Suppliers;
//    entity Addresses as projection on dashboardeleven.Addresses;
//};


//extend service SupplierService with {
//  entity Oders as projection on remoteservice.Orders;
//}
