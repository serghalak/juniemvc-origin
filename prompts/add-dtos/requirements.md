# Requirements: Refactor to use DTOs with MapStruct

Refactor the existing `Beer` resource implementation to separate the web layer from the persistence layer using Data Transfer Objects (DTOs) and MapStruct for mapping.

## 1. Create BeerDTO
- **Package:** `guru.springframework.juniemvc.model`
- **Class:** `BeerDTO` (Java record or class, follow existing project style). 
- **Properties:** Should mirror all properties from the `Beer` entity.
- **Lombok:** Use `@Data` (or `@Getter`/`@Setter`), `@Builder`, `@NoArgsConstructor`, and `@AllArgsConstructor`.

## 2. Create BeerMapper
- **Package:** `guru.springframework.juniemvc.mappers`
- **Type:** Interface annotated with MapStruct `@Mapper`.
- **Configuration:** 
    - Use Spring component model (`componentModel = "spring"` is already set in `pom.xml`, but can be explicit).
    - Map `Beer` to `BeerDTO` and vice versa.
    - **Mapping Rules:** When mapping from `BeerDTO` to `Beer` entity, ignore the following fields:
        - `id`
        - `createdDate`
        - `updatedDate`
- **Visibility:** Use package-private visibility for the mapper interface.

## 3. Refactor BeerService
- Update the `BeerService` interface and its implementation `BeerServiceImpl` to use `BeerDTO` instead of the `Beer` entity for all method signatures and return types.
- **Mapping:** Use `BeerMapper` within the service implementation to convert between Entities and DTOs.
- **Dependency Injection:** Use constructor-based injection for `BeerMapper` and `BeerRepository`.
- **Visibility:** Ensure implementation is package-private.
- **Transactions:** 
    - Annotate the service class or methods with `@Transactional`.
    - Use `@Transactional(readOnly = true)` for query methods.

## 4. Refactor BeerController
- Update `BeerController` to use `BeerDTO` for all request bodies and response types.
- Ensure no JPA Entities are exposed in the Web layer.
- **Visibility:** Keep the controller and its handler methods package-private where possible.
- **Response Handling:** Use `ResponseEntity` to return appropriate HTTP status codes as per REST principles.

## 5. Validation and Testing
- Ensure existing tests are updated to work with DTOs.
- The application must compile and all tests must pass after the refactoring.
