package guru.springframework.juniemvc.services;

import guru.springframework.juniemvc.model.BeerOrderLineDTO;
import guru.springframework.juniemvc.model.CreateBeerOrderLineCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BeerOrderLineService {
    Page<BeerOrderLineDTO> listOrderLines(Integer orderId, Pageable pageable);
    Optional<BeerOrderLineDTO> getOrderLineById(Integer orderId, Integer lineId);
}
