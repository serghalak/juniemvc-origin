# Plan for Adding Customer Entity

This plan outlines the steps required to implement the Customer entity and its associated RESTful API, as specified in `prompts/add-customer/requirements.md`.

## 1. Database Schema and Persistence
- **Flyway Migration:** Create a new migration script `V2__add_customer.sql` in `src/main/resources/db/migration`.
    - Define the `customer` table with fields: `id`, `version`, `created_date`, `update_date`, `name`, `email`, `phone_number`, `address_line1`, `address_line2`, `city`, `state`, `postal_code`.
    - Add a `customer_id` column to the `beer_order` table and a foreign key constraint to link it to the `customer` table.
- **Customer Entity:** Create `guru.springframework.juniemvc.entities.Customer`.
    - Extend `BaseEntity`.
    - Add all required fields with appropriate Jakarta Persistence annotations.
    - Implement `@OneToMany` relationship with `BeerOrder`.
- **BeerOrder Entity Update:**
    - Add `@ManyToOne` relationship to `Customer`.
- **Repository:** Create `guru.springframework.juniemvc.repositories.CustomerRepository` extending `JpaRepository`.

## 2. Data Transfer and Mapping
- **DTO:** Create `guru.springframework.juniemvc.model.CustomerDTO`.
    - Include all fields from the entity.
    - Add Jakarta Validation annotations as per requirements (e.g., `@NotNull` for name, address line 1, city, state, postal code).
- **Mapper:** Create `guru.springframework.juniemvc.mappers.CustomerMapper` using MapStruct.

## 3. Service Layer
- **Service Interface:** Create `guru.springframework.juniemvc.services.CustomerService`.
    - Define CRUD operations: `listCustomers`, `getCustomerById`, `saveNewCustomer`, `updateCustomerById`, `deleteById`.
- **Service Implementation:** Create `guru.springframework.juniemvc.services.CustomerServiceImpl`.
    - Annotate with `@Service` and `@Transactional`.
    - Use `CustomerRepository` and `CustomerMapper`.

## 4. Web Layer
- **Controller:** Create `guru.springframework.juniemvc.controller.CustomerController`.
    - Annotate with `@RestController` and `@RequestMapping("/api/v1/customer")`.
    - Implement GET, POST, PUT, DELETE mappings.
    - Use `ResponseEntity` for responses.
    - Ensure package-private visibility where appropriate as per guidelines.

## 5. OpenAPI Documentation
- **Schema:** Create `openapi/openapi/components/schemas/Customer.yaml`.
- **Paths:**
    - Create `openapi/openapi/paths/api_v1_customer.yaml`.
    - Create `openapi/openapi/paths/api_v1_customer_{customerId}.yaml`.
- **Main Spec:** Update `openapi/openapi/openapi.yaml` to include new paths and tags.
- **Validation:** Run `npm test` in `openapi/` directory.

## 6. Testing and Verification
- **Unit Tests:**
    - Test `CustomerMapper`.
    - Test `CustomerServiceImpl` using Mockito.
- **Integration Tests:**
    - Create `CustomerControllerIT` to test full API flow with a real database (Testcontainers).
    - Test Repository operations if needed.
- **Verification:** Run all tests and ensure they are green.
