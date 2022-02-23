DROP VIEW IF EXISTS OrderService_Order;

DROP TABLE IF EXISTS orders_Orders;

CREATE TABLE orders_Orders (
  ID INTEGER NOT NULL,
  price INTEGER,
  description NVARCHAR(5000),
  id_supplier INTEGER,
  PRIMARY KEY(ID)
);

CREATE VIEW OrderService_Order AS SELECT
  Orders_0.ID,
  Orders_0.price,
  Orders_0.description,
  Orders_0.id_supplier
FROM orders_Orders AS Orders_0;

