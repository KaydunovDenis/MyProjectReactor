using myspace from '../db/data-model';
using OrdersService from '../srv/external/OrdersService';

service SuppliersService {
    entity Suppliers as projection on myspace.Suppliers;
    entity MyOrders as projection on OrdersService.Orders;
}