package com.mydomain.linkgeneratorsystem.app.company.repository;

import com.mydomain.linkgeneratorsystem.app.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findCompanyByCompanyNameIgnoreCase(String companyName);
}
