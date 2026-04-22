package guru.springframework.juniemvc.mappers;

import guru.springframework.juniemvc.entities.Beer;
import guru.springframework.juniemvc.model.BeerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = BeerOrderLineMapper.class)
public interface BeerMapper {

    @Mapping(target = "updatedDate", source = "updateDate")
    BeerDTO beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDTO dto);
}
