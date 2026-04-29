package guru.springframework.juniemvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.juniemvc.model.BeerOrderShipmentDTO;
import guru.springframework.juniemvc.services.BeerOrderShipmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BeerOrderShipmentController.class)
class BeerOrderShipmentControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    @MockitoBean
    BeerOrderShipmentService beerOrderShipmentService;

    @Test
    void listShipments() throws Exception {
        given(beerOrderShipmentService.listShipments(eq(1), any()))
                .willReturn(new PageImpl<>(List.of(BeerOrderShipmentDTO.builder().build())));

        mockMvc.perform(get("/api/v1/beer-orders/1/shipments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getShipmentById() throws Exception {
        BeerOrderShipmentDTO dto = BeerOrderShipmentDTO.builder().id(100).build();
        given(beerOrderShipmentService.getShipmentById(1, 100)).willReturn(dto);

        mockMvc.perform(get("/api/v1/beer-orders/1/shipments/100"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void createShipment() throws Exception {
        BeerOrderShipmentDTO dto = BeerOrderShipmentDTO.builder().shipmentDate(OffsetDateTime.parse("2026-04-29T15:00:00Z")).build();
        given(beerOrderShipmentService.createShipment(eq(1), any())).willReturn(dto);

        mockMvc.perform(post("/api/v1/beer-orders/1/shipments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"shipmentDate\":\"2026-04-29T15:00:00Z\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void updateShipment() throws Exception {
        BeerOrderShipmentDTO dto = BeerOrderShipmentDTO.builder().shipmentDate(OffsetDateTime.parse("2026-04-29T15:00:00Z")).build();
        given(beerOrderShipmentService.updateShipment(eq(1), eq(100), any())).willReturn(dto);

        mockMvc.perform(put("/api/v1/beer-orders/1/shipments/100")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"shipmentDate\":\"2026-04-29T15:00:00Z\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteShipment() throws Exception {
        mockMvc.perform(delete("/api/v1/beer-orders/1/shipments/100"))
                .andExpect(status().isNoContent());
    }
}
