package guru.springframework.juniemvc.services;

import guru.springframework.juniemvc.model.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<CustomerDTO> listCustomers();

    Optional<CustomerDTO> getCustomerById(Integer id);

    CustomerDTO saveNewCustomer(CustomerDTO customer);

    CustomerDTO updateCustomerById(Integer customerId, CustomerDTO customer);

    Boolean deleteById(Integer customerId);
}
