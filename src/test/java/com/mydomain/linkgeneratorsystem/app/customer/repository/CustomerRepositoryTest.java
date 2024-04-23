package com.mydomain.linkgeneratorsystem.app.customer.repository;

import com.mydomain.linkgeneratorsystem.app.company.model.Company;
import com.mydomain.linkgeneratorsystem.app.company.repository.CompanyRepository;
import com.mydomain.linkgeneratorsystem.app.customer.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles(value = "test")
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CompanyRepository companyRepository;

    private Company savedCompany;

    private Customer savedCustomer;

    @BeforeEach
    void setUp() {
        Company company = new Company();
        company.setCompanyEmail("toskabenedict@gmail.com");
        company.setCompanyName("Prizeat");
        company.setContactNumber("08033010000");
        company.setContactAddress("371, Herbert Macaulay, Yaba, Lagos");

        savedCompany = companyRepository.save(company);

        Customer customer = new Customer();
        customer.setCompany(savedCompany);
        customer.setCustomerEmail("ben@gmail.com");
        customer.setFirstName("Ben");
        customer.setLastName("Toska");

        savedCustomer = customerRepository.save(customer);
    }

    @Test
    void findCustomerByCustomerEmailTest() {
        //given
        //when
        Optional<Customer> queryCustomer = customerRepository
                .findCustomerByCustomerEmail("ben@gmail.com");

        //then
        assertThat(queryCustomer.isPresent()).isTrue();
        assertThat(queryCustomer.get().getFirstName()).isEqualTo("Ben");

    }

    @Test
    void findCustomersByCompany() {
        //given

        //when
        List<Customer> customerList = customerRepository.findCustomersByCompany(savedCompany);

        //given
        assertThat(customerList.size()).isNotEqualTo(0);
    }
}