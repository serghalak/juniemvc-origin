package guru.springframework.juniemvc.services;

import guru.springframework.juniemvc.model.BeerDTO;

import java.util.List;
import java.util.Optional;

public interface BeerService {

    List<BeerDTO> listBeers();

    Optional<BeerDTO> getBeerById(Integer id);

    BeerDTO saveNewBeer(BeerDTO beer);

    Optional<BeerDTO> updateBeerById(Integer beerId, BeerDTO beer);

    Boolean deleteById(Integer beerId);
}
