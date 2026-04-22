package guru.springframework.juniemvc.mappers;

import guru.springframework.juniemvc.entities.Beer;
import guru.springframework.juniemvc.entities.BeerOrderLine;
import guru.springframework.juniemvc.model.BeerOrderLineDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BeerOrderLineMapper {
    @Mapping(target = "beerId", source = "beer.id")
    BeerOrderLineDTO beerOrderLineToBeerOrderLineDto(BeerOrderLine beerOrderLine);

    @Mapping(target = "beer", expression = "java(mapBeerIdToBeer(dto.getBeerId()))")
    @Mapping(target = "beerOrder", ignore = true)
    BeerOrderLine beerOrderLineDtoToBeerOrderLine(BeerOrderLineDTO dto);

    default Beer mapBeerIdToBeer(Integer beerId) {
        if (beerId == null) {
            return null;
        }
        Beer beer = new Beer();
        beer.setId(beerId);
        return beer;
    }
}
