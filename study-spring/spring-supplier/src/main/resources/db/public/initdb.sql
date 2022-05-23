create table if not exists addresses
(
    id integer
        constraint addresses_pkey
            primary key,
    country varchar(30) not null
        constraint addresses_country_check
            check (length((country)::text) > 3),
    city varchar(30) not null
        constraint addresses_city_check
            check (length((city)::text) > 3),
    streat varchar(30) not null
        constraint addresses_streat_check
            check (length((streat)::text) > 3),
    building integer not null
        constraint addresses_building_check
            check (building > 0)
);

create table if not exists suppliers
(
    id integer
        constraint suppliers_pkey
            primary key,
    name varchar(30) not null
        constraint suppliers_name_check
            check (length((name)::text) > 3),
    address integer
        constraint suppliers_address_key
            unique
);

create table if not exists orders
(
    id integer
        constraint orders_pkey
            primary key,
    name varchar(30) not null
        constraint orders_name_check
            check (length((name)::text) > 3),
    price integer not null
        constraint orders_price_check
            check (price > 0),
    supplier_id integer
);

create table if not exists products
(
    id integer
        constraint products_pkey
            primary key,
    name varchar(30) not null
        constraint products_name_key
            unique
        constraint products_name_check
            check (length((name)::text) > 3),
    price integer not null
        constraint products_price_check
            check (price > 0),
    order_id integer
);

create table if not exists recipients
(
    id serial
        constraint recipients_pkey
            primary key,
    name varchar(30) not null
        constraint recipients_name_key
            unique,
    address integer
        constraint recipients_address_key
            unique
);

create table if not exists supplier_recipient
(
    supplier_id integer not null,
    recipient_id integer not null,
    constraint supplier_recipient_pkey
        primary key (supplier_id, recipient_id)
);