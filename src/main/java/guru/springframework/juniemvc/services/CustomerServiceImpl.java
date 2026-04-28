package guru.springframework.juniemvc.services;

import guru.springframework.juniemvc.entities.Customer;
import guru.springframework.juniemvc.mappers.CustomerMapper;
import guru.springframework.juniemvc.model.CustomerDTO;
import guru.springframework.juniemvc.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> listCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDTO> getCustomerById(Integer id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDto);
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customer) {
        return customerMapper.customerToCustomerDto(
                customerRepository.save(customerMapper.customerDtoToCustomer(customer))
        );
    }

    @Override
    public Optional<CustomerDTO> updateCustomerById(Integer customerId, CustomerDTO customerDto) {
        return customerRepository.findById(customerId).map(foundCustomer -> {
            foundCustomer.setName(customerDto.getName());
            foundCustomer.setEmail(customerDto.getEmail());
            foundCustomer.setPhoneNumber(customerDto.getPhoneNumber());
            foundCustomer.setAddressLine1(customerDto.getAddressLine1());
            foundCustomer.setAddressLine2(customerDto.getAddressLine2());
            foundCustomer.setCity(customerDto.getCity());
            foundCustomer.setState(customerDto.getState());
            foundCustomer.setPostalCode(customerDto.getPostalCode());
            foundCustomer.setVersion(customerDto.getVersion());
            return customerMapper.customerToCustomerDto(customerRepository.save(foundCustomer));
        });
    }

    @Override
    public Boolean deleteById(Integer customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            return true;
        }
        return false;
    }
}
