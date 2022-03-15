namespace dashboardeleven;

//using {OrderService as remoteservice } from '../srv/external/OrderService';

entity Suppliers {
   key supplierID  :  Integer;
   name  :  String(50) not null;
  // description  :  String(300);
   //address  :  Association to Addresses;
   //TODO как тут прописать ордер? ведь мы не имеем типа ордеров?
//   order: Association to remoteservice.Orders;
};

//entity Addresses {
//   key addressID  :  Integer;
//   country  :  String(50);
//   city  :  String(50);
//   street  :  String(50);
//   house  :  Integer;
//   apartment: Integer;
//   suppliers  : Association to many Suppliers
//                on suppliers.address = $self;
//}
