package guru.springframework.juniemvc.controller;

import guru.springframework.juniemvc.model.BeerOrderShipmentDTO;
import guru.springframework.juniemvc.services.BeerOrderShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer-orders/{beerOrderId}/shipments")
class BeerOrderShipmentController {

    private final BeerOrderShipmentService beerOrderShipmentService;

    @GetMapping
    ResponseEntity<Page<BeerOrderShipmentDTO>> listShipments(@PathVariable Integer beerOrderId, Pageable pageable) {
        return ResponseEntity.ok(beerOrderShipmentService.listShipments(beerOrderId, pageable));
    }

    @GetMapping("/{shipmentId}")
    ResponseEntity<BeerOrderShipmentDTO> getShipmentById(@PathVariable Integer beerOrderId, @PathVariable Integer shipmentId) {
        return ResponseEntity.ok(beerOrderShipmentService.getShipmentById(beerOrderId, shipmentId));
    }

    @PostMapping
    ResponseEntity<BeerOrderShipmentDTO> createShipment(@PathVariable Integer beerOrderId,
                                                      @Validated @RequestBody BeerOrderShipmentDTO shipmentDTO) {
        BeerOrderShipmentDTO savedDto = beerOrderShipmentService.createShipment(beerOrderId, shipmentDTO);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    @PutMapping("/{shipmentId}")
    ResponseEntity<BeerOrderShipmentDTO> updateShipment(@PathVariable Integer beerOrderId,
                                                      @PathVariable Integer shipmentId,
                                                      @Validated @RequestBody BeerOrderShipmentDTO shipmentDTO) {
        return ResponseEntity.ok(beerOrderShipmentService.updateShipment(beerOrderId, shipmentId, shipmentDTO));
    }

    @DeleteMapping("/{shipmentId}")
    ResponseEntity<Void> deleteShipment(@PathVariable Integer beerOrderId, @PathVariable Integer shipmentId) {
        beerOrderShipmentService.deleteShipment(beerOrderId, shipmentId);
        return ResponseEntity.noContent().build();
    }
}
