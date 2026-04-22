package guru.springframework.juniemvc.mappers;

import guru.springframework.juniemvc.entities.Beer;
import guru.springframework.juniemvc.entities.BeerOrder;
import guru.springframework.juniemvc.entities.BeerOrderLine;
import guru.springframework.juniemvc.model.BeerOrderDTO;
import guru.springframework.juniemvc.model.BeerOrderLineDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BeerOrderMapperTest {

    @Autowired
    BeerOrderMapper beerOrderMapper;

    @Test
    void testBeerOrderToBeerOrderDto() {
        Beer beer = Beer.builder().beerName("Test Beer").build();
        beer.setId(1);

        BeerOrderLine line = BeerOrderLine.builder()
                .beer(beer)
                .orderQuantity(10)
                .build();
        line.setId(100);

        BeerOrder order = BeerOrder.builder()
                .customerRef("Customer 1")
                .paymentAmount(new BigDecimal("99.99"))
                .status("NEW")
                .build();
        order.setId(500);
        order.addBeerOrderLine(line);

        BeerOrderDTO dto = beerOrderMapper.beerOrderToBeerOrderDto(order);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(500);
        assertThat(dto.getCustomerRef()).isEqualTo("Customer 1");
        assertThat(dto.getBeerOrderLines()).hasSize(1);
        
        BeerOrderLineDTO lineDto = dto.getBeerOrderLines().iterator().next();
        assertThat(lineDto.getBeerId()).isEqualTo(1);
        assertThat(lineDto.getOrderQuantity()).isEqualTo(10);
    }
}
