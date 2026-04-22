package guru.springframework.juniemvc.mappers;

import guru.springframework.juniemvc.entities.BeerOrder;
import guru.springframework.juniemvc.model.BeerOrderDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = BeerOrderLineMapper.class)
public interface BeerOrderMapper {
    BeerOrderDTO beerOrderToBeerOrderDto(BeerOrder beerOrder);
    BeerOrder beerOrderDtoToBeerOrder(BeerOrderDTO dto);
}
