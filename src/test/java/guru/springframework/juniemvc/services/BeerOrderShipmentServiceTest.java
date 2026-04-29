package guru.springframework.juniemvc.services;

import guru.springframework.juniemvc.entities.BeerOrder;
import guru.springframework.juniemvc.exceptions.NotFoundException;
import guru.springframework.juniemvc.model.BeerOrderShipmentDTO;
import guru.springframework.juniemvc.repositories.BeerOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BeerOrderShipmentServiceTest {

    @Autowired
    BeerOrderShipmentService service;

    @Autowired
    BeerOrderRepository beerOrderRepository;

    BeerOrder beerOrder;

    @BeforeEach
    void setUp() {
        beerOrder = beerOrderRepository.save(BeerOrder.builder()
                .customerRef("Test Order")
                .build());
    }

    @Test
    @Transactional
    void listShipments() {
        service.createShipment(beerOrder.getId(), BeerOrderShipmentDTO.builder()
                .shipmentDate(OffsetDateTime.now())
                .carrier("UPS")
                .build());

        Page<BeerOrderShipmentDTO> shipments = service.listShipments(beerOrder.getId(), PageRequest.of(0, 10));

        assertThat(shipments.getContent()).hasSize(1);
    }

    @Test
    @Transactional
    void getShipmentById() {
        BeerOrderShipmentDTO saved = service.createShipment(beerOrder.getId(), BeerOrderShipmentDTO.builder()
                .shipmentDate(OffsetDateTime.now())
                .carrier("FedEx")
                .build());

        BeerOrderShipmentDTO fetched = service.getShipmentById(beerOrder.getId(), saved.id());

        assertThat(fetched).isNotNull();
        assertThat(fetched.carrier()).isEqualTo("FedEx");
    }

    @Test
    @Transactional
    void createShipment() {
        BeerOrderShipmentDTO saved = service.createShipment(beerOrder.getId(), BeerOrderShipmentDTO.builder()
                .shipmentDate(OffsetDateTime.now())
                .carrier("DHL")
                .trackingNumber("TRK123")
                .build());

        assertThat(saved.id()).isNotNull();
        assertThat(saved.carrier()).isEqualTo("DHL");
    }

    @Test
    @Transactional
    void updateShipment() {
        BeerOrderShipmentDTO saved = service.createShipment(beerOrder.getId(), BeerOrderShipmentDTO.builder()
                .shipmentDate(OffsetDateTime.now())
                .carrier("Original")
                .build());

        BeerOrderShipmentDTO update = BeerOrderShipmentDTO.builder()
                .shipmentDate(OffsetDateTime.now())
                .carrier("Updated")
                .build();

        BeerOrderShipmentDTO updated = service.updateShipment(beerOrder.getId(), saved.id(), update);

        assertThat(updated.carrier()).isEqualTo("Updated");
    }

    @Test
    @Transactional
    void deleteShipment() {
        BeerOrderShipmentDTO saved = service.createShipment(beerOrder.getId(), BeerOrderShipmentDTO.builder()
                .shipmentDate(OffsetDateTime.now())
                .build());

        service.deleteShipment(beerOrder.getId(), saved.id());

        assertThrows(NotFoundException.class, () -> service.getShipmentById(beerOrder.getId(), saved.id()));
    }

    @Test
    void testGetShipmentNotFound() {
        assertThrows(NotFoundException.class, () -> service.getShipmentById(beerOrder.getId(), 9999));
    }
}
