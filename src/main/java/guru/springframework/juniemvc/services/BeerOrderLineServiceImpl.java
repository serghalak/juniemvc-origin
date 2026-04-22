package guru.springframework.juniemvc.services;

import guru.springframework.juniemvc.mappers.BeerOrderLineMapper;
import guru.springframework.juniemvc.model.BeerOrderLineDTO;
import guru.springframework.juniemvc.repositories.BeerOrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class BeerOrderLineServiceImpl implements BeerOrderLineService {

    private final BeerOrderLineRepository beerOrderLineRepository;
    private final BeerOrderLineMapper beerOrderLineMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<BeerOrderLineDTO> listOrderLines(Integer orderId, Pageable pageable) {
        return beerOrderLineRepository.findAllByBeerOrderId(orderId, pageable)
                .map(beerOrderLineMapper::beerOrderLineToBeerOrderLineDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BeerOrderLineDTO> getOrderLineById(Integer orderId, Integer lineId) {
        return beerOrderLineRepository.findById(lineId)
                .filter(line -> line.getBeerOrder().getId().equals(orderId))
                .map(beerOrderLineMapper::beerOrderLineToBeerOrderLineDto);
    }
}
