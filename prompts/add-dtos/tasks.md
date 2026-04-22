# Tasks: Refactor to use DTOs with MapStruct

## Phase 1: Model and Mapper Layer
1. [ ] Create `BeerDTO` in `guru.springframework.juniemvc.model`
    - [ ] Add Lombok annotations: `@Data`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor`
    - [ ] Add fields mirroring `Beer` entity
2. [ ] Create `BeerMapper` interface in `guru.springframework.juniemvc.mappers`
    - [ ] Annotate with `@Mapper(componentModel = "spring")`
    - [ ] Set visibility to package-private
    - [ ] Define `beerDtoToBeer` method with `@Mapping` to ignore `id`, `createdDate`, and `updatedDate`
    - [ ] Define `beerToBeerDto` method

## Phase 2: Service Layer Refactor
3. [ ] Update `BeerService` interface signatures to use `BeerDTO`
4. [ ] Update `BeerServiceImpl` implementation
    - [ ] Change visibility to package-private
    - [ ] Implement constructor injection for `BeerRepository` and `BeerMapper`
    - [ ] Add `@Transactional(readOnly = true)` to `listBeers` and `getBeerById`
    - [ ] Add `@Transactional` to `saveNewBeer`, `updateBeerById`, and `deleteById`
    - [ ] Update methods logic to use `BeerMapper` for DTO/Entity conversion

## Phase 3: Web Layer Refactor
5. [ ] Update `BeerController`
    - [ ] Change visibility of class and methods to package-private
    - [ ] Update handler methods to use `BeerDTO` in parameters and return types
    - [ ] Ensure all handler methods return `ResponseEntity`
    - [ ] Verify URL mapping consistency for `/api/v1/beer`

## Phase 4: Test and Verification
6. [ ] Update `BeerControllerTest` to reflect DTO and API changes
7. [ ] Update `BeerServiceTest` to reflect DTO and API changes
8. [ ] Update `BeerRepositoryTest` if necessary
9. [ ] Run `./mvnw clean compile` to verify MapStruct generation and compilation
10. [ ] Run `./mvnw test` to ensure all tests pass
