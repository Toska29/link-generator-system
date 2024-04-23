package com.mydomain.linkgeneratorsystem.app.company.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.mydomain.linkgeneratorsystem.app.company.dto.OnboardCompanyRequest;
import com.mydomain.linkgeneratorsystem.app.company.model.Company;
import com.mydomain.linkgeneratorsystem.app.company.repository.CompanyRepository;
import com.mydomain.linkgeneratorsystem.exception.LinkGeneratorException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService{
    private CompanyRepository companyRepository;
    private ModelMapper modelMapper;
    private ObjectMapper objectMapper;

    @Override
    public Company onBoardCompany(OnboardCompanyRequest companyRequest) {
        Optional<Company> queryCompany = companyRepository
                .findCompanyByCompanyNameIgnoreCase(companyRequest.getCompanyName());

        if (queryCompany.isPresent()) throw new LinkGeneratorException("company already exist");

        Company company = modelMapper.map(companyRequest, Company.class);

        return companyRepository.save(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new LinkGeneratorException("company does  not exist"));
    }

    @Override
    public Company getCompanyByName(String companyName) {
        return companyRepository.findCompanyByCompanyNameIgnoreCase(companyName)
                .orElseThrow(()-> new LinkGeneratorException("company does not exist"));

    }

    @Override
    public List<Company> allCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company updateCompanyDetails(Long id, JsonPatch jsonPatch) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new LinkGeneratorException("company does not exist"));
        try {
            JsonNode applyPatch = jsonPatch.apply(objectMapper.valueToTree(company));
            Company updatedCompany = objectMapper.treeToValue(applyPatch, Company.class);
            return companyRepository.save(updatedCompany);
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new LinkGeneratorException(e.getMessage());
        }
    }
}
