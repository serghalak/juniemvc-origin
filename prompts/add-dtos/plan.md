# Plan: Refactor to use DTOs with MapStruct

This plan outlines the steps required to refactor the `Beer` resource to use Data Transfer Objects (DTOs) and MapStruct, as specified in `requirements.md` and following the Spring Boot Guidelines.

## Phase 1: Model and Mapper Layer
1.  **Create `BeerDTO`**
    *   Path: `src/main/java/guru/springframework/juniemvc/model/BeerDTO.java`
    *   Implementation: Create a POJO with Lombok `@Data`, `@Builder`, `@NoArgsConstructor`, and `@AllArgsConstructor`.
    *   Fields: Mirror all fields from `Beer` entity.
2.  **Create `BeerMapper`**
    *   Path: `src/main/java/guru/springframework/juniemvc/mappers/BeerMapper.java`
    *   Implementation: Interface with `@Mapper(componentModel = "spring")`.
    *   Visibility: Package-private.
    *   Methods: `beerDtoToBeer` and `beerToBeerDto`.
    *   Rules: Use `@Mapping(target = "id", ignore = true)`, etc., for `createdDate` and `updatedDate` in `beerDtoToBeer`.

## Phase 2: Service Layer Refactor
1.  **Update `BeerService` Interface**
    *   Path: `src/main/java/guru/springframework/juniemvc/services/BeerService.java`
    *   Change method signatures to use `BeerDTO` instead of `Beer` entity.
2.  **Update `BeerServiceImpl`**
    *   Path: `src/main/java/guru/springframework/juniemvc/services/BeerServiceImpl.java`
    *   Inject `BeerMapper` and `BeerRepository` via constructor.
    *   Visibility: Set class to package-private.
    *   Transaction Management: Add `@Transactional` and `@Transactional(readOnly = true)` annotations.
    *   Logic: Update methods to perform mapping between DTOs and Entities.

## Phase 3: Web Layer Refactor
1.  **Update `BeerController`**
    *   Path: `src/main/java/guru/springframework/juniemvc/controller/BeerController.java`
    *   Method Signatures: Change parameters and return types to `BeerDTO` or `ResponseEntity<BeerDTO>`.
    *   REST Compliance: Ensure all methods return `ResponseEntity`.
    *   Visibility: Ensure class and methods are package-private.

## Phase 4: Test and Verification
1.  **Update Unit and Integration Tests**
    *   Update `BeerControllerTest`.
    *   Update `BeerServiceTest`.
    *   Update `BeerRepositoryTest` (if any logic changed).
2.  **Build and Run Tests**
    *   Execute `./mvnw clean compile` to ensure MapStruct implementation is generated.
    *   Execute `./mvnw test` to verify all changes.
