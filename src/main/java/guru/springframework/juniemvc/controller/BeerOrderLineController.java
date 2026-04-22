package guru.springframework.juniemvc.controller;

import guru.springframework.juniemvc.model.BeerOrderLineDTO;
import guru.springframework.juniemvc.services.BeerOrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer-orders/{orderId}/lines")
class BeerOrderLineController {

    private final BeerOrderLineService beerOrderLineService;

    @GetMapping
    public ResponseEntity<Page<BeerOrderLineDTO>> listOrderLines(@PathVariable Integer orderId, Pageable pageable) {
        return ResponseEntity.ok(beerOrderLineService.listOrderLines(orderId, pageable));
    }

    @GetMapping("/{lineId}")
    public ResponseEntity<BeerOrderLineDTO> getOrderLineById(@PathVariable Integer orderId,
                                                           @PathVariable Integer lineId) {
        return beerOrderLineService.getOrderLineById(orderId, lineId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
