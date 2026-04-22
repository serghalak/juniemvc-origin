package guru.springframework.juniemvc.services;

import guru.springframework.juniemvc.model.BeerOrderDTO;
import guru.springframework.juniemvc.model.CreateBeerOrderCommand;
import guru.springframework.juniemvc.model.UpdateBeerOrderCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BeerOrderService {
    Page<BeerOrderDTO> listOrders(Pageable pageable);
    Optional<BeerOrderDTO> getOrderById(Integer id);
    BeerOrderDTO createOrder(CreateBeerOrderCommand command);
    Optional<BeerOrderDTO> updateOrder(Integer id, UpdateBeerOrderCommand command);
    Boolean deleteOrder(Integer id);
}
