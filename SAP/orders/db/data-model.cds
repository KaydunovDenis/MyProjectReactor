namespace orders;

entity Orders {
  key ID : Integer;
  price: Integer;
  description  : String;
  id_supplier : Integer;
}