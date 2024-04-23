package com.mydomain.linkgeneratorsystem.app.company.repository;

import com.mydomain.linkgeneratorsystem.app.company.model.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles(value = "test")
class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;


    @Test
    void testThatCompanyCanBeFoundUsingCompanyName() {
        //given
        Company company = new Company();
        company.setCompanyEmail("toskabenedict@gmail.com");
        company.setCompanyName("Prizeat");
        company.setContactNumber("08033010000");
        company.setContactAddress("371, Herbert Macaulay, Yaba, Lagos");

        companyRepository.save(company);

        //when
        Optional<Company> queryCompany = companyRepository.findCompanyByCompanyNameIgnoreCase("Prizeat");

        //then
        assertThat(queryCompany.get()).isNotNull();
        assertThat(queryCompany.get().getId()).isNotNull();
        assertThat(queryCompany.get().getCompanyName()).isEqualTo("Prizeat");
    }
}