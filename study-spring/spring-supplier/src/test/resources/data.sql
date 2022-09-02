TRUNCATE TABLE addresses;
TRUNCATE TABLE orders;
TRUNCATE TABLE  products;
TRUNCATE TABLE  recipients;
TRUNCATE TABLE  supplier_recipient;
TRUNCATE TABLE suppliers;

INSERT INTO addresses (id, country, city, street, building)
VALUES (1, 'Belarus', 'Gomel', 'Covetskaya', 32),
       (2, 'Belarus', 'Gomel', 'Covetskaya', 23433),
       (3, 'Belarus', 'Gomel', 'Covetskaya', 234),
       (4, 'Russia', 'Moskow', 'Derebasovka', 323);

INSERT INTO suppliers (name, address_id)
VALUES ('Supplier1', 1),
       ('Diana', 2),
       ('Alina', 3),
       ('Diana', 4);

INSERT INTO orders (name, price, supplier_id)
VALUES ('order1', 100, 1),
       ('order2', 23, 2),
       ('order3', 34, 3),
       ('order4', 423, 1);

INSERT INTO products (name, price, order_id)
VALUES ('product1', 323, 1),
       ('product2', 34, 1),
       ('product3', 43, 2),
       ('product4', 4, 2),
       ('product5', 27, 2),
       ('product5', 27, 1);

INSERT INTO recipients (name, address_id)
VALUES ('recipient1', 1),
       ('recipient2', 2),
       ('recipient3', 3),
       ('recipient4', 4);

INSERT INTO supplier_recipient
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (1, 2),
       (1, 3),
       (2, 4),
       (1, 4);