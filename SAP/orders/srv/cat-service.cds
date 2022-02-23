using orders from '../db/data-model';

service OrderService {
    @readonly entity Order as projection on orders.Orders;
}