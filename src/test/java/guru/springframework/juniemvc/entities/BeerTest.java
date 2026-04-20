package guru.springframework.juniemvc.entities;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class BeerTest {

    @Test
    void testBeerLombok() {
        Beer beer = Beer.builder()
                .beerName("Galaxy IPA")
                .beerStyle("IPA")
                .upc("123456")
                .price(new BigDecimal("12.95"))
                .quantityOnHand(100)
                .build();

        assertNotNull(beer);
        assertEquals("Galaxy IPA", beer.getBeerName());
        assertEquals("IPA", beer.getBeerStyle());
        assertEquals("123456", beer.getUpc());
        assertEquals(new BigDecimal("12.95"), beer.getPrice());
        assertEquals(100, beer.getQuantityOnHand());
    }

    @Test
    void testNoArgsConstructor() {
        Beer beer = new Beer();
        assertNotNull(beer);
    }
}
