# Tasks: Refactor to use DTOs with MapStruct

## Phase 1: Model and Mapper Layer
1. [x] Create `BeerDTO` in `guru.springframework.juniemvc.model`
    - [x] Add Lombok annotations: `@Data`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor`
    - [x] Add fields mirroring `Beer` entity
2. [x] Create `BeerMapper` interface in `guru.springframework.juniemvc.mappers`
    - [x] Annotate with `@Mapper(componentModel = "spring")`
    - [x] Set visibility to package-private
    - [x] Define `beerDtoToBeer` method with `@Mapping` to ignore `id`, `createdDate`, and `updatedDate`
    - [x] Define `beerToBeerDto` method

## Phase 2: Service Layer Refactor
3. [x] Update `BeerService` interface signatures to use `BeerDTO`
4. [x] Update `BeerServiceImpl` implementation
    - [x] Change visibility to package-private
    - [x] Implement constructor injection for `BeerRepository` and `BeerMapper`
    - [x] Add `@Transactional(readOnly = true)` to `listBeers` and `getBeerById`
    - [x] Add `@Transactional` to `saveNewBeer`, `updateBeerById`, and `deleteById`
    - [x] Update methods logic to use `BeerMapper` for DTO/Entity conversion

## Phase 3: Web Layer Refactor
5. [x] Update `BeerController`
    - [x] Change visibility of class and methods to package-private
    - [x] Update handler methods to use `BeerDTO` in parameters and return types
    - [x] Ensure all handler methods return `ResponseEntity`
    - [x] Verify URL mapping consistency for `/api/v1/beer`

## Phase 4: Test and Verification
6. [x] Update `BeerControllerTest` to reflect DTO and API changes
7. [x] Update `BeerServiceTest` to reflect DTO and API changes
8. [x] Update `BeerRepositoryTest` if necessary
9. [x] Run `./mvnw clean compile` to verify MapStruct generation and compilation
10. [x] Run `./mvnw test` to ensure all tests pass
