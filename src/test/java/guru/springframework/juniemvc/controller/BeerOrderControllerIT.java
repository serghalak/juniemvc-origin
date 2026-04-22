package guru.springframework.juniemvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import guru.springframework.juniemvc.model.BeerOrderDTO;
import guru.springframework.juniemvc.model.CreateBeerOrderCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import guru.springframework.juniemvc.services.BeerOrderService;
import guru.springframework.juniemvc.services.BeerOrderLineService;

@WebMvcTest({BeerOrderController.class, BeerOrderLineController.class})
class BeerOrderControllerIT {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    BeerOrderService beerOrderService;

    @MockitoBean
    BeerOrderLineService beerOrderLineService;

    @Test
    void testCreateOrder() throws Exception {
        CreateBeerOrderCommand command = new CreateBeerOrderCommand("Test Customer", new BigDecimal("10.00"), Set.of());

        mockMvc.perform(post("/api/v1/beer-orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isCreated());
    }

    @Test
    void testListOrders() throws Exception {
        mockMvc.perform(get("/api/v1/beer-orders"))
                .andExpect(status().isOk());
    }
}
