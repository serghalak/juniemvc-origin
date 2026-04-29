# Plan for Adding BeerOrderShipment

This plan outlines the steps required to add the `BeerOrderShipment` entity and its associated RESTful API, following the project's Spring Boot and OpenAPI guidelines.

## 1. Database Schema and Persistence Layer
- Create a Flyway migration script `V3__add_order_shipment.sql` to:
    - Create the `beer_order_shipment` table with columns: `id`, `version`, `created_date`, `last_modified_date`, `shipment_date` (NOT NULL), `carrier`, `tracking_number`, and `beer_order_id` (foreign key).
- Create the `BeerOrderShipment` JPA entity:
    - Extend `BaseEntity`.
    - Annotate with `@Entity`, `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@Builder`.
    - Add fields: `shipmentDate` (OffsetDateTime), `carrier` (String), `trackingNumber` (String).
    - Add `@ManyToOne` relationship to `BeerOrder`.
- Update the `BeerOrder` entity:
    - Add `@OneToMany` relationship to `BeerOrderShipment` with `mappedBy`, `cascade = CascadeType.ALL`, and `orphanRemoval = true`.
    - Add helper methods `addShipment` and `removeShipment`.
- Create `BeerOrderShipmentRepository` interface extending `JpaRepository`.

## 2. DTO and Mapping Layer
- Create `BeerOrderShipmentDTO` record to represent the shipment data.
- Create `BeerOrderShipmentMapper` using MapStruct to convert between entity and DTO.
    - Include an update method with `@MappingTarget` to handle updates, ignoring `id` and timestamps.

## 3. Service Layer
- Create `BeerOrderShipmentService` interface with CRUD operations:
    - `listShipments(Integer beerOrderId, Pageable pageable)`
    - `getShipmentById(Integer beerOrderId, Integer shipmentId)`
    - `createShipment(Integer beerOrderId, BeerOrderShipmentDTO shipmentDTO)`
    - `updateShipment(Integer beerOrderId, Integer shipmentId, BeerOrderShipmentDTO shipmentDTO)`
    - `deleteShipment(Integer beerOrderId, Integer shipmentId)`
- Create `BeerOrderShipmentServiceImpl` to implement the interface, using constructor injection for dependencies.

## 4. Web Layer
- Create `BeerOrderShipmentController`:
    - Annotate with `@RestController` and `@RequestMapping("/api/v1/beer-orders/{beerOrderId}/shipments")`.
    - Use package-private visibility for the class and its methods.
    - Inject `BeerOrderShipmentService` via constructor.
    - Implement methods for GET (list and single), POST, PUT, and DELETE.
    - Use `ResponseEntity` for all responses.

## 5. OpenAPI Specification
- Create `openapi/openapi/components/schemas/BeerOrderShipment.yaml`.
- Create `openapi/openapi/paths/api_v1_beer-orders_{beerOrderId}_shipments.yaml`.
- Create `openapi/openapi/paths/api_v1_beer-orders_{beerOrderId}_shipments_{shipmentId}.yaml`.
- Update `openapi/openapi/openapi.yaml` to include the new paths and tag group.
- Verify the specification using `npm test`.

## 6. Testing
- Create `BeerOrderShipmentMapperTest` to verify mapping logic.
- Create `BeerOrderShipmentServiceTest` (Integration Test) using `@SpringBootTest` and Testcontainers.
- Create `BeerOrderShipmentControllerTest` using `MockMvc` to verify API endpoints and status codes.
- Ensure all tests pass.
