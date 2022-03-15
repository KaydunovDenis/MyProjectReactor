Task

This service must get data from other service by ID. 

This service: port: 4004
    dashboard eleven -> OwnerService
    is the consumer
        Supplier has ID of an order

Other service: port: 8081
    orders -> OrderService
    entity:
        int id,
        int price
