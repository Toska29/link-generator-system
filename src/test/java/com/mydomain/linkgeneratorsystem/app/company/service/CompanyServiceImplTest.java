package com.mydomain.linkgeneratorsystem.app.company.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mydomain.linkgeneratorsystem.app.company.dto.OnboardCompanyRequest;
import com.mydomain.linkgeneratorsystem.app.company.model.Company;
import com.mydomain.linkgeneratorsystem.app.company.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;

    private ModelMapper modelMapper;

    private ObjectMapper objectMapper;

    private CompanyService companyService;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        objectMapper = new ObjectMapper().findAndRegisterModules();

        companyService = new CompanyServiceImpl(companyRepository, modelMapper, objectMapper);
    }

    @Test
    void onBoardCompanyTest() {
        //given
        OnboardCompanyRequest companyRequest = new OnboardCompanyRequest();
        companyRequest.setCompanyEmail("Prizeat");
        companyRequest.setContactAddress("371, Herbert Macaulay, Yaba, Lagos");
        companyRequest.setContactNumber("08033010000");

        Company company = new Company();
        company.setCompanyEmail("toskabenedict@gmail.com");
        company.setCompanyName("Prizeat");
        company.setContactNumber("08033010000");
        company.setContactAddress("371, Herbert Macaulay, Yaba, Lagos");

        when(companyRepository.save(any(Company.class))).thenReturn(company);

        //when
        Company onboardCompany = companyService.onBoardCompany(companyRequest);

        //then
        verify(companyRepository).save(any(Company.class));
    }

    @Test
    void getCompanyByIdTest() {
        //given
        Company company = new Company();
        company.setId(1L);
        company.setCompanyEmail("toskabenedict@gmail.com");
        company.setCompanyName("Prizeat");
        company.setContactNumber("08033010000");
        company.setContactAddress("371, Herbert Macaulay, Yaba, Lagos");

        when(companyRepository.findById(anyLong())).thenReturn(Optional.of(company));

        //when
        Company queryCompany = companyService.getCompanyById(1L);

        //then
        verify(companyRepository).findById(anyLong());
        assertThat(queryCompany.getId()).isEqualTo(1L);
        assertThat(queryCompany.getCompanyName()).isEqualTo("Prizeat");
    }

    @Test
    void getCompanyByNameTest() {
        //given
        Company company = new Company();
        company.setId(1L);
        company.setCompanyEmail("toskabenedict@gmail.com");
        company.setCompanyName("Prizeat");
        company.setContactNumber("08033010000");
        company.setContactAddress("371, Herbert Macaulay, Yaba, Lagos");

        when(companyRepository.findCompanyByCompanyNameIgnoreCase(anyString()))
                .thenReturn(Optional.of(company));

        //when
        Company queryCompany = companyService.getCompanyByName("Prizeat");

        //then
        verify(companyRepository).findCompanyByCompanyNameIgnoreCase(anyString());
        assertThat(queryCompany.getCompanyName()).isEqualTo("Prizeat");
    }

    @Test
    void allCompaniesTest() {
        //given
        Company prizeatCompany = new Company();
        prizeatCompany.setId(1L);
        prizeatCompany.setCompanyEmail("toskabenedict@gmail.com");
        prizeatCompany.setCompanyName("Prizeat");
        prizeatCompany.setContactNumber("08033010000");
        prizeatCompany.setContactAddress("371, Herbert Macaulay, Yaba, Lagos");

        Company mainOneCompany = new Company();
        mainOneCompany.setId(2L);
        mainOneCompany.setCompanyEmail("toska@gmail.com");
        mainOneCompany.setCompanyName("mainOne");
        mainOneCompany.setContactNumber("08033010001");
        mainOneCompany.setContactAddress("377, Herbert Macaulay, Yaba, Lagos");

        when(companyRepository.findAll()).thenReturn(List.of(prizeatCompany, mainOneCompany));

        //when
        List<Company> companies = companyService.allCompanies();

        //then
        verify(companyRepository).findAll();
        assertThat(companies.size()).isEqualTo(2);
    }

}