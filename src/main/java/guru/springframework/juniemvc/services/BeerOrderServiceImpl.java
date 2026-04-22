package guru.springframework.juniemvc.services;

import guru.springframework.juniemvc.entities.Beer;
import guru.springframework.juniemvc.entities.BeerOrder;
import guru.springframework.juniemvc.entities.BeerOrderLine;
import guru.springframework.juniemvc.mappers.BeerOrderMapper;
import guru.springframework.juniemvc.model.BeerOrderDTO;
import guru.springframework.juniemvc.model.CreateBeerOrderCommand;
import guru.springframework.juniemvc.model.UpdateBeerOrderCommand;
import guru.springframework.juniemvc.repositories.BeerOrderRepository;
import guru.springframework.juniemvc.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class BeerOrderServiceImpl implements BeerOrderService {

    private final BeerOrderRepository beerOrderRepository;
    private final BeerRepository beerRepository;
    private final BeerOrderMapper beerOrderMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<BeerOrderDTO> listOrders(Pageable pageable) {
        return beerOrderRepository.findAll(pageable)
                .map(beerOrderMapper::beerOrderToBeerOrderDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BeerOrderDTO> getOrderById(Integer id) {
        return beerOrderRepository.findById(id)
                .map(beerOrderMapper::beerOrderToBeerOrderDto);
    }

    @Override
    @Transactional
    public BeerOrderDTO createOrder(CreateBeerOrderCommand command) {
        BeerOrder beerOrder = BeerOrder.builder()
                .customerRef(command.customerRef())
                .paymentAmount(command.paymentAmount())
                .status("NEW")
                .build();

        if (command.beerOrderLines() != null) {
            command.beerOrderLines().forEach(lineCommand -> {
                Beer beer = beerRepository.findById(lineCommand.beerId())
                        .orElseThrow(() -> new RuntimeException("Beer not found"));
                
                BeerOrderLine line = BeerOrderLine.builder()
                        .beer(beer)
                        .orderQuantity(lineCommand.orderQuantity())
                        .status("NEW")
                        .build();
                
                beerOrder.addBeerOrderLine(line);
            });
        }

        return beerOrderMapper.beerOrderToBeerOrderDto(beerOrderRepository.save(beerOrder));
    }

    @Override
    @Transactional
    public Optional<BeerOrderDTO> updateOrder(Integer id, UpdateBeerOrderCommand command) {
        return beerOrderRepository.findById(id).map(foundOrder -> {
            foundOrder.setCustomerRef(command.customerRef());
            foundOrder.setPaymentAmount(command.paymentAmount());
            foundOrder.setStatus(command.status());
            return beerOrderMapper.beerOrderToBeerOrderDto(beerOrderRepository.save(foundOrder));
        });
    }

    @Override
    @Transactional
    public Boolean deleteOrder(Integer id) {
        if (beerOrderRepository.existsById(id)) {
            beerOrderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
