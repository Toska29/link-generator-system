package com.mydomain.linkgeneratorsystem.app.customer.controller;

import com.mydomain.linkgeneratorsystem.app.customer.dto.request.AddCustomerDto;
import com.mydomain.linkgeneratorsystem.app.customer.dto.response.CustomerResponseDto;
import com.mydomain.linkgeneratorsystem.app.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<?> addCustomerToCompany(@RequestBody AddCustomerDto customerDto) {
        CustomerResponseDto customerResponseDto = customerService.addCustomerToCompany(customerDto);

        return new ResponseEntity<>(customerResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        CustomerResponseDto customerResponseDto = customerService.getCustomerById(id);

        return ResponseEntity.ok(customerResponseDto);
    }

    @GetMapping("/company")
    public ResponseEntity<?> getCustomersByCompany(@RequestParam(name = "value") String companyName) {
        List<CustomerResponseDto> customersByCompany = customerService.getCustomersByCompany(companyName);

        return ResponseEntity.ok(customersByCompany);
    }
}
