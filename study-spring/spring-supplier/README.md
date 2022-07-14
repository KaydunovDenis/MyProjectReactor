SupplierApplication

Application runs locally
Use Spring DATA JPA

### 1 DB
Postgres from Docker
Schema according image: database_architecture.JPEG

### 2 SupplierService
+ create entity
Написать SupplierService implementation CRUD операции
+ получить просто Supplier без зависимых сущностей
+ получить Supplier и все зависимые сущности

### 3 Junit Tests for SupplierService
JUnit на уровень сервисов без использования Mockito
Use DB H2 for testing

List or Set what use in Hibernate and way?


### Adoption with MapStruct
or SpringConvertor

### 4 Controller
Написать 4-e Controller для Supplier согласно уровням Richardson Maturity Model
При необходимости создать layer DAO

###Mock WEB MVC тесты для контроллера


### Oauth 2
USER
ADMIN - Delete is available only for ADMIN

### Aдоптировать  тесты под oauth 2

###Note
Supplier need extend from Representation Class
