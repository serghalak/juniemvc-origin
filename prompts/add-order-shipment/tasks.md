# Tasks for Adding BeerOrderShipment

## 1. Database Schema and Persistence Layer
- [x] Create Flyway migration script `src/main/resources/db/migration/V3__add_order_shipment.sql` [x]
- [x] Create `BeerOrderShipment` entity in `src/main/java/guru/springframework/juniemvc/entities/BeerOrderShipment.java` [x]
- [x] Update `BeerOrder` entity with `@OneToMany` relationship in `src/main/java/guru/springframework/juniemvc/entities/BeerOrder.java` [x]
- [x] Create `BeerOrderShipmentRepository` in `src/main/java/guru/springframework/juniemvc/repositories/BeerOrderShipmentRepository.java` [x]

## 2. DTO and Mapping Layer
- [x] Create `BeerOrderShipmentDTO` in `src/main/java/guru/springframework/juniemvc/model/BeerOrderShipmentDTO.java` [x]
- [x] Create `BeerOrderShipmentMapper` in `src/main/java/guru/springframework/juniemvc/mappers/BeerOrderShipmentMapper.java` [x]

## 3. Service Layer
- [x] Create `BeerOrderShipmentService` interface in `src/main/java/guru/springframework/juniemvc/services/BeerOrderShipmentService.java` [x]
- [x] Create `BeerOrderShipmentServiceImpl` in `src/main/java/guru/springframework/juniemvc/services/BeerOrderShipmentServiceImpl.java` [x]

## 4. Web Layer
- [x] Create `BeerOrderShipmentController` in `src/main/java/guru/springframework/juniemvc/controller/BeerOrderShipmentController.java` [x]

## 5. OpenAPI Specification
- [x] Create schema `openapi/openapi/components/schemas/BeerOrderShipment.yaml` [x]
- [x] Create path `openapi/openapi/paths/api_v1_beer-orders_{beerOrderId}_shipments.yaml` [x]
- [x] Create path `openapi/openapi/paths/api_v1_beer-orders_{beerOrderId}_shipments_{shipmentId}.yaml` [x]
- [x] Update `openapi/openapi/openapi.yaml` with new paths and tags [x]
- [x] Run `npm test` in `openapi/` directory to validate specification [x]

## 6. Testing
- [x] Create `BeerOrderShipmentMapperTest` in `src/test/java/guru/springframework/juniemvc/mappers/BeerOrderShipmentMapperTest.java` [x]
- [x] Create `BeerOrderShipmentServiceTest` in `src/test/java/guru/springframework/juniemvc/services/BeerOrderShipmentServiceTest.java` [x]
- [x] Create `BeerOrderShipmentControllerTest` in `src/test/java/guru/springframework/juniemvc/controller/BeerOrderShipmentControllerTest.java` [x]
- [x] Run all tests and verify they pass [x]
