package guru.springframework.juniemvc.controller;

import guru.springframework.juniemvc.model.CustomerDTO;
import guru.springframework.juniemvc.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    ResponseEntity<List<CustomerDTO>> listCustomers() {
        return new ResponseEntity<>(customerService.listCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("customerId") Integer customerId) {
        return new ResponseEntity<>(customerService.getCustomerById(customerId)
                .orElseThrow(RuntimeException::new), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<CustomerDTO> handlePost(@RequestBody @Validated CustomerDTO customer) {
        CustomerDTO savedCustomer = customerService.saveNewCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    ResponseEntity<Void> updateById(@PathVariable("customerId") Integer customerId,
                                    @RequestBody @Validated CustomerDTO customer) {
        customerService.updateCustomerById(customerId, customer);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{customerId}")
    ResponseEntity<Void> deleteById(@PathVariable("customerId") Integer customerId) {
        if (!customerService.deleteById(customerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
