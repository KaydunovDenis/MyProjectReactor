using myspace from '../db/data-model';

service OrdersService {
    entity Orders as projection on myspace.Orders;
}