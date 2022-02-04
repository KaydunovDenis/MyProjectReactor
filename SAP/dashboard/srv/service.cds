using dashboard from '../db/schema';

service Main {
    entity Suppliers as projection on dashboard.Suppliers;
    entity Addresses as projection on dashboard.Addresses;
}