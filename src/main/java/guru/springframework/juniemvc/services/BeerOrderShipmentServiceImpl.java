package guru.springframework.juniemvc.services;

import guru.springframework.juniemvc.entities.BeerOrder;
import guru.springframework.juniemvc.entities.BeerOrderShipment;
import guru.springframework.juniemvc.exceptions.NotFoundException;
import guru.springframework.juniemvc.mappers.BeerOrderShipmentMapper;
import guru.springframework.juniemvc.model.BeerOrderShipmentDTO;
import guru.springframework.juniemvc.repositories.BeerOrderRepository;
import guru.springframework.juniemvc.repositories.BeerOrderShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BeerOrderShipmentServiceImpl implements BeerOrderShipmentService {

    private final BeerOrderShipmentRepository beerOrderShipmentRepository;
    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderShipmentMapper beerOrderShipmentMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<BeerOrderShipmentDTO> listShipments(Integer beerOrderId, Pageable pageable) {
        if (!beerOrderRepository.existsById(beerOrderId)) {
            throw new NotFoundException();
        }
        return beerOrderShipmentRepository.findAllByBeerOrderId(beerOrderId, pageable)
                .map(beerOrderShipmentMapper::beerOrderShipmentToBeerOrderShipmentDto);
    }

    @Override
    @Transactional(readOnly = true)
    public BeerOrderShipmentDTO getShipmentById(Integer beerOrderId, Integer shipmentId) {
        return beerOrderShipmentRepository.findById(shipmentId)
                .filter(shipment -> shipment.getBeerOrder().getId().equals(beerOrderId))
                .map(beerOrderShipmentMapper::beerOrderShipmentToBeerOrderShipmentDto)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public BeerOrderShipmentDTO createShipment(Integer beerOrderId, BeerOrderShipmentDTO shipmentDTO) {
        BeerOrder beerOrder = beerOrderRepository.findById(beerOrderId)
                .orElseThrow(NotFoundException::new);

        BeerOrderShipment shipment = beerOrderShipmentMapper.beerOrderShipmentDtoToBeerOrderShipment(shipmentDTO);
        shipment.setBeerOrder(beerOrder);

        return beerOrderShipmentMapper.beerOrderShipmentToBeerOrderShipmentDto(
                beerOrderShipmentRepository.save(shipment)
        );
    }

    @Override
    @Transactional
    public BeerOrderShipmentDTO updateShipment(Integer beerOrderId, Integer shipmentId, BeerOrderShipmentDTO shipmentDTO) {
        BeerOrderShipment existing = beerOrderShipmentRepository.findById(shipmentId)
                .filter(shipment -> shipment.getBeerOrder().getId().equals(beerOrderId))
                .orElseThrow(NotFoundException::new);

        beerOrderShipmentMapper.updateBeerOrderShipmentFromDto(shipmentDTO, existing);

        return beerOrderShipmentMapper.beerOrderShipmentToBeerOrderShipmentDto(
                beerOrderShipmentRepository.save(existing)
        );
    }

    @Override
    @Transactional
    public void deleteShipment(Integer beerOrderId, Integer shipmentId) {
        BeerOrderShipment shipment = beerOrderShipmentRepository.findById(shipmentId)
                .filter(s -> s.getBeerOrder().getId().equals(beerOrderId))
                .orElseThrow(NotFoundException::new);

        beerOrderShipmentRepository.delete(shipment);
    }
}
