TRUNCATE TABLE addresses;
TRUNCATE TABLE suppliers;

INSERT INTO addresses (country, city, street, building) VALUES ('Belarus', 'Gomel', 'Covetskaya', 32);
INSERT INTO addresses (country, city, street, building) VALUES ('Belarus', 'Gomel', 'Covetskaya', 23433);
INSERT INTO addresses (country, city, street, building) VALUES ('Belarus', 'Gomel', 'Covetskaya', 234);
INSERT INTO addresses (country, city, street, building) VALUES ('Russia', 'Moskow', 'Derebasovka', 323);

INSERT INTO suppliers (name, address_id) VALUES ('Supplier1', 1);
INSERT INTO suppliers (name, address_id) VALUES ('Dima', 2);
INSERT INTO suppliers (name, address_id) VALUES ('Alina', 3);
INSERT INTO suppliers (name, address_id) VALUES ('Diana', 4);