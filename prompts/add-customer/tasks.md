# Customer Implementation Tasks

## 1. Create Customer Entity
- [x] Create a new `Customer` JPA entity that extends `BaseEntity`
- [x] Implement the following properties:
    - `name` (String, not null)
    - `email` (String)
    - `phoneNumber` (String)
    - `addressLine1` (String, not null)
    - `addressLine2` (String)
    - `city` (String, not null)
    - `state` (String, not null)
    - `postalCode` (String, not null)
- [x] Establish a `OneToMany` relationship with `BeerOrder`

## 2. Update BeerOrder Entity
- [x] Add a `ManyToOne` relationship to `Customer`
- [x] Update the `BeerOrder` entity to reference `Customer` instead of using `customerRef`

## 3. Create Flyway Migration Script
- [x] Create a new migration script (`V2__add_customer.sql`) to:
    - Create the `customer` table with all required fields
    - Alter the `beer_order` table to add a foreign key reference to the `customer` table

## 4. Create DTO and Mapper
- [x] Create `CustomerDTO` class
- [x] Create `CustomerMapper` interface using MapStruct
- [x] Implement bidirectional mapping between `Customer` entity and `CustomerDTO`

## 5. Create Repository
- [x] Create `CustomerRepository` interface extending `JpaRepository`

## 6. Create Service Layer
- [x] Create `CustomerService` interface
- [x] Create `CustomerServiceImpl` class implementing `CustomerService`
- [x] Implement CRUD operations:
    - `listCustomers`
    - `getCustomerById`
    - `saveNewCustomer`
    - `updateCustomerById`
    - `deleteById`

## 7. Create Controller
- [x] Create `CustomerController` class
- [x] Implement RESTful endpoints:
    - `GET /api/v1/customer` - List all customers
    - `GET /api/v1/customer/{customerId}` - Get customer by ID
    - `POST /api/v1/customer` - Create new customer
    - `PUT /api/v1/customer/{customerId}` - Update existing customer
    - `DELETE /api/v1/customer/{customerId}` - Delete customer

## 8. Update OpenAPI Documentation
- [x] Add `Customer` tag to `openapi.yaml`
- [x] Create path files for Customer operations:
    - `api_v1_customer.yaml` (GET all, POST)
    - `api_v1_customer_{customerId}.yaml` (GET by ID, PUT, DELETE)
- [x] Create schema file for `Customer` (DTO)

## 9. Write Tests
- [x] Write unit tests for `CustomerMapper`
- [x] Write unit tests for `CustomerService` (Mocking repository)
- [x] Write unit tests for `CustomerController` (MockMvc)
- [x] Verify integration behavior (Flyway migrations, Repository)

## 10. Verify Implementation
- [x] Run all tests to ensure they pass
- [x] Verify that the application builds successfully
- [x] Validate OpenAPI specification using `npm test`
