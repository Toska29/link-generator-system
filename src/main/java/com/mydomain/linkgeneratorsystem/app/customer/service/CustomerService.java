package com.mydomain.linkgeneratorsystem.app.customer.service;

import com.mydomain.linkgeneratorsystem.app.customer.dto.request.AddCustomerDto;
import com.mydomain.linkgeneratorsystem.app.customer.dto.response.CustomerResponseDto;
import com.mydomain.linkgeneratorsystem.app.customer.model.Customer;

import java.util.List;

public interface CustomerService {
    CustomerResponseDto addCustomerToCompany(AddCustomerDto customerDto);

    CustomerResponseDto getCustomerById(Long id);

    Customer findCustomerByEmail(String email);

    List<CustomerResponseDto> getCustomersByCompany(String companyName);
}
