package guru.springframework.juniemvc.services;

import guru.springframework.juniemvc.model.BeerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class BeerServiceTest {

    @Autowired
    BeerService beerService;

    @Test
    @Transactional
    void testListBeers() {
        beerService.saveNewBeer(BeerDTO.builder()
                .beerName("Beer 1")
                .beerStyle("IPA")
                .upc("123")
                .price(new BigDecimal("12.99"))
                .build());

        List<BeerDTO> beers = beerService.listBeers();

        assertThat(beers.size()).isGreaterThan(0);
    }

    @Test
    @Transactional
    void testGetBeerById() {
        BeerDTO savedBeer = beerService.saveNewBeer(BeerDTO.builder()
                .beerName("Beer 1")
                .beerStyle("IPA")
                .upc("123")
                .price(new BigDecimal("12.99"))
                .build());

        Optional<BeerDTO> beerOptional = beerService.getBeerById(savedBeer.getId());

        assertThat(beerOptional).isPresent();
        assertThat(beerOptional.get().getId()).isEqualTo(savedBeer.getId());
    }

    @Test
    @Transactional
    void testSaveNewBeer() {
        BeerDTO savedBeer = beerService.saveNewBeer(BeerDTO.builder()
                .beerName("New Beer")
                .beerStyle("PALE_ALE")
                .upc("456")
                .price(new BigDecimal("11.99"))
                .build());

        assertThat(savedBeer.getId()).isNotNull();
    }

    @Test
    @Transactional
    void testUpdateBeerById() {
        BeerDTO savedBeer = beerService.saveNewBeer(BeerDTO.builder()
                .beerName("Old Name")
                .beerStyle("LAGER")
                .upc("111")
                .price(new BigDecimal("9.99"))
                .build());

        BeerDTO beerUpdate = BeerDTO.builder()
                .beerName("New Name")
                .beerStyle("STOUT")
                .upc("222")
                .price(new BigDecimal("10.99"))
                .build();

        Optional<BeerDTO> updatedBeerOptional = beerService.updateBeerById(savedBeer.getId(), beerUpdate);

        assertThat(updatedBeerOptional).isPresent();
        assertThat(updatedBeerOptional.get().getBeerName()).isEqualTo("New Name");
        assertThat(updatedBeerOptional.get().getBeerStyle()).isEqualTo("STOUT");
    }

    @Test
    @Transactional
    void testDeleteById() {
        BeerDTO savedBeer = beerService.saveNewBeer(BeerDTO.builder()
                .beerName("To Delete")
                .beerStyle("IPA")
                .upc("333")
                .price(new BigDecimal("12.99"))
                .build());

        Boolean deleted = beerService.deleteById(savedBeer.getId());

        assertThat(deleted).isTrue();
        assertThat(beerService.getBeerById(savedBeer.getId())).isEmpty();
    }
}
