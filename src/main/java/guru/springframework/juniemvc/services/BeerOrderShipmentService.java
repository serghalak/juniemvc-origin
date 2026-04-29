package guru.springframework.juniemvc.services;

import guru.springframework.juniemvc.model.BeerOrderShipmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BeerOrderShipmentService {
    Page<BeerOrderShipmentDTO> listShipments(Integer beerOrderId, Pageable pageable);

    BeerOrderShipmentDTO getShipmentById(Integer beerOrderId, Integer shipmentId);

    BeerOrderShipmentDTO createShipment(Integer beerOrderId, BeerOrderShipmentDTO shipmentDTO);

    BeerOrderShipmentDTO updateShipment(Integer beerOrderId, Integer shipmentId, BeerOrderShipmentDTO shipmentDTO);

    void deleteShipment(Integer beerOrderId, Integer shipmentId);
}
