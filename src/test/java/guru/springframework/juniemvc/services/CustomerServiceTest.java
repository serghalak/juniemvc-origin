package guru.springframework.juniemvc.services;

import guru.springframework.juniemvc.exceptions.NotFoundException;
import guru.springframework.juniemvc.model.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Test
    @Transactional
    void testListCustomers() {
        customerService.saveNewCustomer(CustomerDTO.builder()
                .name("Customer 1")
                .addressLine1("Addr 1")
                .city("City 1")
                .state("ST")
                .postalCode("12345")
                .build());

        List<CustomerDTO> customers = customerService.listCustomers();

        assertThat(customers.size()).isGreaterThan(0);
    }

    @Test
    @Transactional
    void testGetCustomerById() {
        CustomerDTO savedCustomer = customerService.saveNewCustomer(CustomerDTO.builder()
                .name("Customer 1")
                .addressLine1("Addr 1")
                .city("City 1")
                .state("ST")
                .postalCode("12345")
                .build());

        Optional<CustomerDTO> customerOptional = customerService.getCustomerById(savedCustomer.getId());

        assertThat(customerOptional).isPresent();
        assertThat(customerOptional.get().getId()).isEqualTo(savedCustomer.getId());
    }

    @Test
    @Transactional
    void testSaveNewCustomer() {
        CustomerDTO savedCustomer = customerService.saveNewCustomer(CustomerDTO.builder()
                .name("New Customer")
                .addressLine1("New Addr")
                .city("New City")
                .state("NY")
                .postalCode("11111")
                .build());

        assertThat(savedCustomer.getId()).isNotNull();
    }

    @Test
    @Transactional
    void testUpdateCustomerById() {
        CustomerDTO savedCustomer = customerService.saveNewCustomer(CustomerDTO.builder()
                .name("Old Name")
                .addressLine1("Old Addr")
                .city("Old City")
                .state("ST")
                .postalCode("00000")
                .build());

        CustomerDTO customerUpdate = CustomerDTO.builder()
                .id(999) // Attempt to update ID
                .name("New Name")
                .addressLine1("New Addr")
                .city("New City")
                .state("NY")
                .postalCode("11111")
                .build();

        CustomerDTO updatedCustomer = customerService.updateCustomerById(savedCustomer.getId(), customerUpdate);

        assertThat(updatedCustomer).isNotNull();
        assertThat(updatedCustomer.getName()).isEqualTo("New Name");
        assertThat(updatedCustomer.getId()).isEqualTo(savedCustomer.getId());
        assertThat(updatedCustomer.getId()).isNotEqualTo(999);
    }

    @Test
    @Transactional
    void testDeleteById() {
        CustomerDTO savedCustomer = customerService.saveNewCustomer(CustomerDTO.builder()
                .name("To Delete")
                .addressLine1("Addr")
                .city("City")
                .state("ST")
                .postalCode("00000")
                .build());

        Boolean deleted = customerService.deleteById(savedCustomer.getId());

        assertThat(deleted).isTrue();
        assertThat(customerService.getCustomerById(savedCustomer.getId())).isEmpty();
    }

    @Test
    @Transactional
    void testUpdateCustomerNotFound() {
        assertThrows(NotFoundException.class, () -> {
            customerService.updateCustomerById(999, CustomerDTO.builder().name("New Name").build());
        });
    }
}
