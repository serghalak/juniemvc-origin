package guru.springframework.juniemvc.services;

import guru.springframework.juniemvc.entities.Beer;

import java.util.List;
import java.util.Optional;

public interface BeerService {

    List<Beer> listBeers();

    Optional<Beer> getBeerById(Integer id);

    Beer saveNewBeer(Beer beer);

    Optional<Beer> updateBeerById(Integer beerId, Beer beer);

    Boolean deleteById(Integer beerId);
}
