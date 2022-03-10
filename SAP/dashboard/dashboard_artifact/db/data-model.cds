namespace myspace;
using OrdersService from '../srv/external/OrdersService';

entity Suppliers {
   key supplier_ID : Integer;
   name  : String(100);
   orders: Association to many OrdersService.Orders;
}

