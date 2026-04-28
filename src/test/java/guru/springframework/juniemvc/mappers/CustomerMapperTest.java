package guru.springframework.juniemvc.mappers;

import guru.springframework.juniemvc.entities.Customer;
import guru.springframework.juniemvc.model.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomerMapperTest {

    @Autowired
    CustomerMapper customerMapper;

    @Test
    void testCustomerToCustomerDto() {
        Customer customer = Customer.builder()
                .name("John Doe")
                .email("john@example.com")
                .phoneNumber("555-1234")
                .addressLine1("123 Main St")
                .city("New York")
                .state("NY")
                .postalCode("10001")
                .build();
        customer.setId(1);

        CustomerDTO dto = customerMapper.customerToCustomerDto(customer);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(1);
        assertThat(dto.getName()).isEqualTo("John Doe");
        assertThat(dto.getEmail()).isEqualTo("john@example.com");
    }

    @Test
    void testCustomerDtoToCustomer() {
        CustomerDTO dto = CustomerDTO.builder()
                .name("Jane Doe")
                .email("jane@example.com")
                .addressLine1("456 Oak St")
                .city("London")
                .state("Greater London")
                .postalCode("SW1A 1AA")
                .build();

        Customer customer = customerMapper.customerDtoToCustomer(dto);

        assertThat(customer).isNotNull();
        assertThat(customer.getName()).isEqualTo("Jane Doe");
        assertThat(customer.getEmail()).isEqualTo("jane@example.com");
        assertThat(customer.getAddressLine1()).isEqualTo("456 Oak St");
    }
}
