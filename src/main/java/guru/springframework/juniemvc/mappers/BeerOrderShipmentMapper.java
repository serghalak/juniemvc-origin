package guru.springframework.juniemvc.mappers;

import guru.springframework.juniemvc.entities.BeerOrderShipment;
import guru.springframework.juniemvc.model.BeerOrderShipmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface BeerOrderShipmentMapper {

    BeerOrderShipment beerOrderShipmentDtoToBeerOrderShipment(BeerOrderShipmentDTO dto);

    BeerOrderShipmentDTO beerOrderShipmentToBeerOrderShipmentDto(BeerOrderShipment entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "beerOrder", ignore = true)
    BeerOrderShipment updateBeerOrderShipmentFromDto(BeerOrderShipmentDTO dto, @MappingTarget BeerOrderShipment entity);
}
