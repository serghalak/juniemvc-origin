# Requirements: Beer Order System Implementation

## Overview
Update the project to support a Beer Order system by implementing new JPA entities, DTOs, mappers, repositories, services, and RESTful controllers. All implementations must adhere to the project's Spring Boot Guidelines.

## 1. Persistence Layer (JPA Entities)

### Common Base Entity
Create a package-private `BaseEntity` in `guru.springframework.juniemvc.entities` to handle common fields.

```java
@MappedSuperclass
@Getter
@Setter
abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Version
    private Integer version;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;
}
```

### Beer Entity
Update existing `Beer` entity to extend `BaseEntity` and add relationship to `BeerOrderLine`.
*   Maintain existing fields: `beerName`, `beerStyle`, `upc`, `quantityOnHand`, `price`.
*   Add `@OneToMany(mappedBy = "beer")` relationship to `BeerOrderLine`.

### BeerOrder Entity
Create package-private `BeerOrder` entity extending `BaseEntity`.
*   Fields: `customerRef` (String), `paymentAmount` (BigDecimal, precision 19, scale 2), `status` (String).
*   Relationship: `@OneToMany(mappedBy = "beerOrder", cascade = CascadeType.ALL, orphanRemoval = true)` to `BeerOrderLine`.
*   Use `@Builder.Default` for collection initialization.

### BeerOrderLine Entity
Create package-private `BeerOrderLine` entity extending `BaseEntity`.
*   Fields: `orderQuantity` (Integer), `quantityAllocated` (Integer), `status` (String).
*   Relationships: 
    *   `@ManyToOne` to `BeerOrder` (owning side).
    *   `@ManyToOne` to `Beer` (owning side).

## 2. Model Layer (DTOs & Command Objects)

### DTOs
Create `BeerOrderDTO` and `BeerOrderLineDTO` in `guru.springframework.juniemvc.model`.
*   Follow `camelCase` for JSON properties.
*   Include validation annotations (e.g., `@NotBlank`, `@NotNull`, `@Positive`).
*   Ensure top-level data structure is a JSON object.

### Command Objects
Create Command records for business operations:
*   `CreateBeerOrderCommand`
*   `UpdateBeerOrderCommand`
*   `CreateBeerOrderLineCommand`

## 3. Mapping Layer (MapStruct)
Create/Update MapStruct mappers in `guru.springframework.juniemvc.mappers`:
*   `BeerOrderMapper`: Convert between `BeerOrder` and `BeerOrderDTO`.
*   `BeerOrderLineMapper`: Convert between `BeerOrderLine` and `BeerOrderLineDTO`.

## 4. Repository Layer
Create Spring Data JPA repositories in `guru.springframework.juniemvc.repositories`:
*   `BeerOrderRepository`
*   `BeerOrderLineRepository`
*   Ensure they are package-private.

## 5. Service Layer
Implement services in `guru.springframework.juniemvc.services`:
*   Interfaces: `BeerOrderService`, `BeerOrderLineService`.
*   Implementations: `BeerOrderServiceImpl`, `BeerOrderLineServiceImpl`.
*   **Requirements**:
    *   Use **Constructor Injection** for all dependencies.
    *   Methods should be marked `@Transactional`.
    *   Query methods marked `@Transactional(readOnly = true)`.
    *   Accept Command objects for creation/updates.
    *   Implement pagination for collection resources.

## 6. Web Layer (Controllers)
Create REST Controllers in `guru.springframework.juniemvc.controller`:
*   `BeerOrderController`: Endpoint `/api/v1/beer-orders`.
*   `BeerOrderLineController`: Endpoint `/api/v1/beer-orders/{orderId}/lines`.
*   **Requirements**:
    *   Package-private visibility.
    *   Return `ResponseEntity<T>` with appropriate HTTP status codes (200 OK, 201 Created, 404 Not Found, etc.).
    *   Use versioned, resource-oriented URLs.
    *   Implement pagination for collection endpoints.

## 7. Testing
Implement comprehensive integration tests using **Testcontainers**.
*   Test mappers, repositories, services, and controllers.
*   Use `@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)`.
*   Ensure all tests are green.

## 8. General Constraints
*   **No OSIV**: Ensure `spring.jpa.open-in-view=false` is set.
*   **Encapsulation**: Prefer package-private over public for all Spring components.
*   **Logging**: Use SLF4J, guard expensive log calls.
*   **Lombok**: Use `@Getter`, `@Setter`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor`. Avoid `@Data` on entities.
