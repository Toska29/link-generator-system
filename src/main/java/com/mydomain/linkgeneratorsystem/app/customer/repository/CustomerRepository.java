package com.mydomain.linkgeneratorsystem.app.customer.repository;

import com.mydomain.linkgeneratorsystem.app.company.model.Company;
import com.mydomain.linkgeneratorsystem.app.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByCustomerEmail(String email);

    List<Customer> findCustomersByCompany(Company company);
}
