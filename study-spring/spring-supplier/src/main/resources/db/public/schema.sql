create table if not exists suppliers
(
    id         SERIAL PRIMARY KEY ,
    name       varchar(30) not null,
    address_id integer     NOT NULL UNIQUE
);

create table if not exists addresses
(
    id       SERIAL PRIMARY KEY,
    country  varchar(30) not null,
    city     varchar(30) not null,
    street   varchar(30) not null,
    building integer     not null
);

create table if not exists orders
(
    id          SERIAL PRIMARY KEY,
    name        varchar(30) not null,
    price       integer     not null,
    supplier_id integer
);

create table if not exists products
(
    id       SERIAL PRIMARY KEY,
    name     varchar(30) not null,
    price    integer     not null,
    order_id integer
);

create table if not exists recipients
(
    id         SERIAL PRIMARY KEY,
    name       varchar(30) not null unique,
    address_id integer unique
);

create table if not exists supplier_recipient
(
    supplier_id  integer not null,
    recipient_id integer not null,
    constraint supplier_recipient_pkey
        PRIMARY KEY (supplier_id, recipient_id)
);