# Tasks for Adding Customer Entity

## 1. Database and Persistence
- [x] 1.1 Create Flyway migration script `src/main/resources/db/migration/V2__add_customer.sql`.
- [x] 1.2 Implement `Customer` entity in `src/main/java/guru/springframework/juniemvc/entities/Customer.java`.
- [x] 1.3 Update `BeerOrder` entity in `src/main/java/guru/springframework/juniemvc/entities/BeerOrder.java` to include `Customer` relationship.
- [x] 1.4 Create `CustomerRepository` in `src/main/java/guru/springframework/juniemvc/repositories/CustomerRepository.java`.

## 2. DTO and Mapping
- [x] 2.1 Create `CustomerDTO` in `src/main/java/guru/springframework/juniemvc/model/CustomerDTO.java`.
- [x] 2.2 Create `CustomerMapper` in `src/main/java/guru/springframework/juniemvc/mappers/CustomerMapper.java`.

## 3. Service Layer
- [x] 3.1 Create `CustomerService` interface in `src/main/java/guru/springframework/juniemvc/services/CustomerService.java`.
- [x] 3.2 Create `CustomerServiceImpl` in `src/main/java/guru/springframework/juniemvc/services/CustomerServiceImpl.java`.

## 4. Web Layer
- [x] 4.1 Create `CustomerController` in `src/main/java/guru/springframework/juniemvc/controller/CustomerController.java`.

## 5. OpenAPI Documentation
- [x] 5.1 Create `openapi/openapi/components/schemas/Customer.yaml`.
- [x] 5.2 Create `openapi/openapi/paths/api_v1_customer.yaml`.
- [x] 5.3 Create `openapi/openapi/paths/api_v1_customer_{customerId}.yaml`.
- [x] 5.4 Update `openapi/openapi/openapi.yaml` with new paths and tags.
- [x] 5.5 Validate OpenAPI spec with `npm test`.

## 6. Testing and Verification
- [x] 6.1 Create `CustomerMapperTest`.
- [x] 6.2 Create `CustomerControllerTest` (MockMvc).
- [x] 6.3 Create `CustomerControllerIT` (Integration Test).
- [x] 6.4 Run all tests and verify passing.
