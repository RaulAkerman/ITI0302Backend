package com.team6.webproject.controller.main;

import com.team6.webproject.dto.add.AddCustomerDto;
import com.team6.webproject.dto.get.CustomerDto;
import com.team6.webproject.dto.update.UpdateCustomerDto;
import com.team6.webproject.repository.customer.CustomerFilter;
import com.team6.webproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/api/customer/getAllCustomers")
    public List<CustomerDto> getAllCustomers(Integer page, String order, String orderBy,String fname,String lastName) {
        CustomerFilter filter = new CustomerFilter(orderBy, order, fname, lastName);
        log.info("Started getAllCustomers service....");
        return customerService.findAll(page, filter);
    }

    @GetMapping("/api/customer/get/{id}")
    public CustomerDto findCustomerById(@PathVariable("id") Integer id) {
        log.info("Started findCustomerById service using id = " + id + "....");
        return customerService.findCustomerById(id);
    }

    @PostMapping("/api/customer/")
    public void saveNewCustomer(@RequestBody AddCustomerDto newCustomer) {
        log.info("Started saveNewUser service for customer = " +
                newCustomer.getFname() + " " +
                newCustomer.getLastName() + "....");
        customerService.saveCustomer(newCustomer);
    }

    @DeleteMapping("/api/customer/delete/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        log.info("Started deleteCustomerService using id = " + id + "....");
        customerService.deleteCustomer(id);
    }

    @PostMapping("/api/customer/update/")
    public void updateCustomer(@RequestBody UpdateCustomerDto updateCustomerDto) {
        log.info("Started updateCustomerService for " +
                updateCustomerDto.getLastName() + " " +
                updateCustomerDto.getLastName() +"....");
        customerService.updateCustomer(updateCustomerDto);
    }
}
