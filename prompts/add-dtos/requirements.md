# Requirements: Refactor to use DTOs with MapStruct

Refactor the existing `Beer` resource implementation to separate the web layer from the persistence layer using Data Transfer Objects (DTOs) and MapStruct for mapping, adhering to the project's Spring Boot Guidelines.

## 1. Create BeerDTO
- **Package:** `guru.springframework.juniemvc.model`
- **Class:** `BeerDTO` (Lombok POJO).
- **Properties:** Mirror all properties from the `Beer` entity (`id`, `version`, `beerName`, `beerStyle`, `upc`, `quantityOnHand`, `price`, `createdDate`, `updatedDate`).
- **Lombok:** Use `@Builder`, `@Data`, `@NoArgsConstructor`, and `@AllArgsConstructor`.

## 2. Create BeerMapper
- **Package:** `guru.springframework.juniemvc.mappers`
- **Type:** Interface annotated with MapStruct `@Mapper`.
- **Configuration:** 
    - Explicitly set `componentModel = "spring"`.
    - Create mapping methods:
        - `Beer beerDtoToBeer(BeerDTO dto)`
        - `BeerDTO beerToBeerDto(Beer beer)`
    - **Mapping Rules:** When mapping from `BeerDTO` to `Beer` entity, ignore the following target fields to avoid overwriting audit/identity data:
        - `id`
        - `createdDate`
        - `updatedDate`
- **Visibility:** Use package-private visibility for the mapper interface.

## 3. Refactor BeerService
- Update `BeerService` interface and `BeerServiceImpl` to use `BeerDTO` for all method parameters and return types.
- **Implementation Details:**
    - Inject `BeerMapper` and `BeerRepository` using constructor injection (no `@Autowired`).
    - Use `BeerMapper` to convert entities to DTOs (and vice-versa) before returning/saving data.
    - **Visibility:** The implementation class should be package-private.
- **Transactions:** 
    - Annotate `BeerServiceImpl` methods with `@Transactional`.
    - Use `@Transactional(readOnly = true)` for read methods (`listBeers`, `getBeerById`).

## 4. Refactor BeerController
- Update `BeerController` to use `BeerDTO` for all request bodies and return types.
- **REST Principles:**
    - Use `ResponseEntity<T>` for all methods.
    - Ensure `listBeers` returns `ResponseEntity<List<BeerDTO>>`.
    - Ensure `handlePost` returns `ResponseEntity` with `HttpStatus.CREATED`.
    - Update URL mapping to follow `/api/v1/beer` (ensure consistency).
- **Visibility:** Keep the controller and handler methods package-private.

## 5. Verification
- Update `BeerControllerTest`, `BeerServiceTest`, and `BeerRepositoryTest` (if affected) to reflect API changes.
- All tests must pass, and the application must compile.
