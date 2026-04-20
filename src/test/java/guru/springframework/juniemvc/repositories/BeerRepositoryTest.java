package guru.springframework.juniemvc.repositories;

import guru.springframework.juniemvc.entities.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                        .beerName("My Beer")
                        .beerStyle("PALE_ALE")
                        .upc("234234234234")
                        .price(new BigDecimal("11.99"))
                .build());

        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();

        Beer fetchedBeer = beerRepository.findById(savedBeer.getId()).get();
        assertThat(fetchedBeer).isNotNull();
        assertThat(fetchedBeer.getBeerName()).isEqualTo("My Beer");
    }

    @Test
    void testUpdateBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                .beerName("Old Name")
                .beerStyle("LAGER")
                .upc("111222")
                .price(new BigDecimal("9.99"))
                .build());

        savedBeer.setBeerName("New Name");
        Beer updatedBeer = beerRepository.save(savedBeer);

        assertThat(updatedBeer.getBeerName()).isEqualTo("New Name");
    }

    @Test
    void testDeleteBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                .beerName("To Be Deleted")
                .beerStyle("IPA")
                .upc("333444")
                .price(new BigDecimal("12.99"))
                .build());

        Integer id = savedBeer.getId();
        beerRepository.delete(savedBeer);

        assertThat(beerRepository.findById(id)).isEmpty();
    }

    @Test
    void testListBeer() {
        beerRepository.save(Beer.builder()
                .beerName("Beer 1")
                .beerStyle("IPA")
                .upc("12345")
                .price(new BigDecimal("12.99"))
                .build());

        beerRepository.save(Beer.builder()
                .beerName("Beer 2")
                .beerStyle("PALE_ALE")
                .upc("123456")
                .price(new BigDecimal("11.99"))
                .build());

        assertThat(beerRepository.findAll().size()).isEqualTo(2);
    }
}
