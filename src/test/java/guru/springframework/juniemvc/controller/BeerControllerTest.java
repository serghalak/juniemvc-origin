package guru.springframework.juniemvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.juniemvc.model.BeerDTO;
import guru.springframework.juniemvc.services.BeerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    BeerService beerService;

    @Test
    void testListBeers() throws Exception {
        given(beerService.listBeers()).willReturn(Arrays.asList(
                BeerDTO.builder().id(1).beerName("Beer 1").build(),
                BeerDTO.builder().id(2).beerName("Beer 2").build()
        ));

        mockMvc.perform(get("/api/v1/beer")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(2)));
    }

    @Test
    void testGetBeerById() throws Exception {
        BeerDTO beer = BeerDTO.builder().id(1).beerName("Beer 1").build();

        given(beerService.getBeerById(any())).willReturn(Optional.of(beer));

        mockMvc.perform(get("/api/v1/beer/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.beerName", is("Beer 1")));
    }

    @Test
    void testCreateNewBeer() throws Exception {
        BeerDTO beer = BeerDTO.builder()
                .beerName("New Beer")
                .beerStyle("IPA")
                .upc("123123")
                .price(new BigDecimal("12.99"))
                .build();

        given(beerService.saveNewBeer(any())).willReturn(BeerDTO.builder().id(1).beerName("New Beer").build());

        mockMvc.perform(post("/api/v1/beer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(beer)))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateBeer() throws Exception {
        BeerDTO beer = BeerDTO.builder().id(1).beerName("Updated Name").build();

        given(beerService.updateBeerById(any(), any())).willReturn(Optional.of(beer));

        mockMvc.perform(put("/api/v1/beer/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(beer)))
                .andExpect(status().isNoContent());

        verify(beerService).updateBeerById(any(), any());
    }

    @Test
    void testDeleteBeer() throws Exception {
        given(beerService.deleteById(any())).willReturn(true);

        mockMvc.perform(delete("/api/v1/beer/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(beerService).deleteById(any());
    }
}
