package guru.springframework.juniemvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.juniemvc.model.CustomerDTO;
import guru.springframework.juniemvc.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    CustomerService customerService;

    @Test
    void testListCustomers() throws Exception {
        given(customerService.listCustomers()).willReturn(Arrays.asList(
                CustomerDTO.builder().id(1).name("Customer 1").build(),
                CustomerDTO.builder().id(2).name("Customer 2").build()
        ));

        mockMvc.perform(get("/api/v1/customer")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(2)));
    }

    @Test
    void testGetCustomerById() throws Exception {
        CustomerDTO customer = CustomerDTO.builder().id(1).name("Customer 1").build();

        given(customerService.getCustomerById(any())).willReturn(Optional.of(customer));

        mockMvc.perform(get("/api/v1/customer/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Customer 1")));
    }

    @Test
    void testCreateNewCustomer() throws Exception {
        CustomerDTO customer = CustomerDTO.builder()
                .name("New Customer")
                .addressLine1("123 Main St")
                .city("New York")
                .state("NY")
                .postalCode("10001")
                .build();

        given(customerService.saveNewCustomer(any())).willReturn(CustomerDTO.builder().id(1).name("New Customer").build());

        mockMvc.perform(post("/api/v1/customer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateCustomer() throws Exception {
        CustomerDTO customer = CustomerDTO.builder()
                .id(1)
                .name("Updated Name")
                .addressLine1("123 Main St")
                .city("New York")
                .state("NY")
                .postalCode("10001")
                .build();

        given(customerService.updateCustomerById(any(), any())).willReturn(Optional.of(customer));

        mockMvc.perform(put("/api/v1/customer/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isNoContent());

        verify(customerService).updateCustomerById(any(), any());
    }

    @Test
    void testDeleteCustomer() throws Exception {
        given(customerService.deleteById(any())).willReturn(true);

        mockMvc.perform(delete("/api/v1/customer/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(customerService).deleteById(any());
    }
}
