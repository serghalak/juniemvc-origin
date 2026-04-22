# Improvement Plan: Beer Order System

This plan outlines the steps required to fully implement and refine the Beer Order system as specified in the `requirements.md` and in accordance with the project's Spring Boot Guidelines.

## 1. Persistence Layer Refinement
- **BaseEntity**: Ensure it is package-private and correctly annotated with `@MappedSuperclass`.
- **Beer Entity**: Verify it extends `BaseEntity` and has the correct bidirectional relationship with `BeerOrderLine`.
- **BeerOrder Entity**: 
    - Ensure package-private visibility.
    - Verify `@OneToMany` relationship with `BeerOrderLine` includes `cascade = CascadeType.ALL` and `orphanRemoval = true`.
    - Implement helper methods `addBeerOrderLine` and `removeBeerOrderLine` for relationship management.
- **BeerOrderLine Entity**:
    - Ensure package-private visibility.
    - Verify `@ManyToOne` relationships to `BeerOrder` and `Beer`.

## 2. Model & Mapping Layer Implementation
- **DTOs**:
    - Verify `BeerOrderDTO` and `BeerOrderLineDTO` follow naming conventions and include Jakarta Validation annotations (`@NotBlank`, `@NotNull`, etc.).
- **Command Objects**:
    - Ensure `CreateBeerOrderCommand`, `UpdateBeerOrderCommand`, and `CreateBeerOrderLineCommand` are implemented as records.
- **MapStruct Mappers**:
    - Refine `BeerOrderMapper` and `BeerOrderLineMapper` to handle all necessary field mappings, including nested DTOs if required.

## 3. Repository & Service Layer Enhancement
- **Repositories**:
    - Ensure `BeerOrderRepository` and `BeerOrderLineRepository` are package-private.
- **Services**:
    - Verify `BeerOrderService` and `BeerOrderLineService` use **Constructor Injection**.
    - Ensure all service methods are properly annotated with `@Transactional` (and `readOnly = true` for queries).
    - Implement pagination in service methods that return collections.
    - Update service implementations to accept Command objects where appropriate.

## 4. Web Layer (REST Controllers)
- **BeerOrderController**:
    - Ensure package-private visibility.
    - Use `ResponseEntity<T>` for all endpoints with correct HTTP status codes.
    - Implement pagination support for the list endpoint.
- **BeerOrderLineController**:
    - Implement endpoints for managing order lines under `/api/v1/beer-orders/{orderId}/lines`.
    - Ensure package-private visibility and correct status codes.

## 5. Infrastructure & Configuration
- **Application Properties**:
    - Confirm `spring.jpa.open-in-view=false` is set.
- **Logging**:
    - Audit existing logs to ensure SLF4J is used and expensive calls are guarded.

## 6. Testing & Validation
- **Integration Tests**:
    - Enhance `BeerOrderControllerIT` to cover all CRUD operations and edge cases.
    - Use **Testcontainers** for database-backed tests.
    - Ensure `@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)` is used.
- **Unit Tests**:
    - Ensure mappers and business logic in services are covered by unit tests.
- **Verification**:
    - Run all tests to ensure project stability and compliance with requirements.
