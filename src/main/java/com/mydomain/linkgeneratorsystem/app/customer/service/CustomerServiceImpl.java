package com.mydomain.linkgeneratorsystem.app.customer.service;

import com.mydomain.linkgeneratorsystem.app.company.model.Company;
import com.mydomain.linkgeneratorsystem.app.company.service.CompanyService;
import com.mydomain.linkgeneratorsystem.app.customer.dto.request.AddCustomerDto;
import com.mydomain.linkgeneratorsystem.app.customer.dto.response.CustomerResponseDto;
import com.mydomain.linkgeneratorsystem.app.customer.model.Customer;
import com.mydomain.linkgeneratorsystem.app.customer.repository.CustomerRepository;
import com.mydomain.linkgeneratorsystem.exception.LinkGeneratorException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private CompanyService companyService;
    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;

    @Override
    public CustomerResponseDto addCustomerToCompany(AddCustomerDto customerDto) {
        Company company = companyService.getCompanyByName(customerDto.getCompanyName());
        Optional<Customer> queryCustomer = customerRepository.findCustomerByCustomerEmail(customerDto.getCustomerEmail());

        if (queryCustomer.isPresent()) throw new LinkGeneratorException("customer already exist");

        Customer customer = modelMapper.map(customerDto, Customer.class);
        customer.setCompany(company);
        Customer savedCustomer = customerRepository.save(customer);

        return buildCustomerResponseDto(savedCustomer);
    }

    @Override
    public CustomerResponseDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new LinkGeneratorException("customer does not exist"));

        return buildCustomerResponseDto(customer);
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return customerRepository.findCustomerByCustomerEmail(email)
                .orElseThrow(() -> new LinkGeneratorException("customer does not exist"));

    }

    @Override
    public List<CustomerResponseDto> getCustomersByCompany(String companyName) {
        Company company = companyService.getCompanyByName(companyName);
        List<Customer> customerList = customerRepository.findCustomersByCompany(company);

        return buildCustomerResponseDtoList(customerList);
    }

    private CustomerResponseDto buildCustomerResponseDto(Customer customer) {
        CustomerResponseDto customerResponseDto = modelMapper.map(customer, CustomerResponseDto.class);
        customerResponseDto.setCompanyId(customer.getCompany().getId());
        customerResponseDto.setCompanyName(customer.getCompany().getCompanyName());

        return customerResponseDto;
    }

    private List<CustomerResponseDto> buildCustomerResponseDtoList(List<Customer> customers) {
        List<CustomerResponseDto> customerResponseDtoList = new ArrayList<>();
        customers.forEach(customer -> customerResponseDtoList.add(buildCustomerResponseDto(customer)));
        return customerResponseDtoList;
    }
}
