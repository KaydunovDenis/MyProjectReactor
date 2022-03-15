
DROP TABLE IF EXISTS OrderService_Orders;

CREATE TABLE OrderService_Orders (
  ID INTEGER NOT NULL,
  price INTEGER,
  idSupplier INTEGER,
  PRIMARY KEY(ID)
);

