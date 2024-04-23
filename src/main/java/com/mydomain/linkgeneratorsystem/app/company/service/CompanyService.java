package com.mydomain.linkgeneratorsystem.app.company.service;

import com.github.fge.jsonpatch.JsonPatch;
import com.mydomain.linkgeneratorsystem.app.company.dto.OnboardCompanyRequest;
import com.mydomain.linkgeneratorsystem.app.company.model.Company;

import java.util.List;

public interface CompanyService {
    Company onBoardCompany(OnboardCompanyRequest companyRequest);

    Company getCompanyById(Long id);

    Company getCompanyByName(String companyName);

    List<Company> allCompanies();

    Company updateCompanyDetails(Long id, JsonPatch jsonPatch);
}