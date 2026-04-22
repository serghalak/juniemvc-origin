# Task List: Beer Order System Implementation

## 1. Persistence Layer Refinement
1. [x] Ensure `BaseEntity` is package-private and annotated with `@MappedSuperclass`.
2. [x] Refactor `Beer` entity to extend `BaseEntity` and verify `@OneToMany` relationship with `BeerOrderLine`.
3. [x] Refactor `BeerOrder` entity:
    - [x] Set visibility to package-private.
    - [x] Verify `@OneToMany` relationship with `BeerOrderLine` includes `cascade = CascadeType.ALL` and `orphanRemoval = true`.
    - [x] Implement helper methods `addBeerOrderLine` and `removeBeerOrderLine`.
4. [x] Refactor `BeerOrderLine` entity:
    - [x] Set visibility to package-private.
    - [x] Verify `@ManyToOne` relationships to `BeerOrder` and `Beer`.

## 2. Model & Mapping Layer Implementation
5. [x] Refine DTOs:
    - [x] Verify `BeerOrderDTO` and `BeerOrderLineDTO` follow camelCase naming.
    - [x] Add Jakarta Validation annotations (`@NotBlank`, `@NotNull`, `@Positive`, etc.) to DTO fields.
6. [x] Implement Command Objects as records:
    - [x] `CreateBeerOrderCommand`
    - [x] `UpdateBeerOrderCommand`
    - [x] `CreateBeerOrderLineCommand`
7. [x] Refine MapStruct Mappers:
    - [x] Update `BeerOrderMapper` for full field mapping.
    - [x] Update `BeerOrderLineMapper` for full field mapping.

## 3. Repository & Service Layer Enhancement
8. [x] Ensure `BeerOrderRepository` and `BeerOrderLineRepository` are package-private (Actually public due to cross-package access).
9. [x] Refine Service Layer:
    - [x] Verify `BeerOrderService` and `BeerOrderLineService` use Constructor Injection.
    - [x] Annotate service methods with `@Transactional` (and `readOnly = true` for queries).
    - [x] Implement pagination in service methods returning collections.
    - [x] Update service methods to accept Command objects.

## 4. Web Layer (REST Controllers)
10. [x] Refine `BeerOrderController`:
    - [x] Set visibility to package-private.
    - [x] Ensure all endpoints return `ResponseEntity<T>` with correct HTTP status codes.
    - [x] Implement pagination support for the list endpoint.
11. [x] Refine `BeerOrderLineController`:
    - [x] Set visibility to package-private.
    - [x] Implement CRUD endpoints under `/api/v1/beer-orders/{orderId}/lines`.
    - [x] Ensure correct status codes and `ResponseEntity<T>` usage.

## 5. Infrastructure & Configuration
12. [x] Confirm `spring.jpa.open-in-view=false` is set in `application.properties`.
13. [x] Audit and guard logging calls using SLF4J in new components.

## 6. Testing & Validation
14. [x] Implement/Enhance Unit Tests:
    - [x] `BeerOrderMapperTest`
    - [x] Service layer unit tests if complex logic exists.
15. [x] Implement/Enhance Integration Tests:
    - [x] Update `BeerOrderControllerIT` to cover all CRUD operations.
    - [x] Use Testcontainers and `@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)`.
16. [x] Run all tests and verify project stability.
