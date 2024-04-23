package com.mydomain.linkgeneratorsystem.app.company.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.mydomain.linkgeneratorsystem.app.company.dto.OnboardCompanyRequest;
import com.mydomain.linkgeneratorsystem.app.company.model.Company;
import com.mydomain.linkgeneratorsystem.app.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/on-board")
    public ResponseEntity<?> onBoardCompany(@RequestBody OnboardCompanyRequest companyRequest) {
        Company company = companyService.onBoardCompany(companyRequest);
        return new ResponseEntity<>(company, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);

        return ResponseEntity.ok(company);
    }

    @GetMapping()
    public ResponseEntity<?> getAllCompanies() {
        List<Company> companies = companyService.allCompanies();

        return ResponseEntity.ok(companies);
    }

    @GetMapping("/name")
    public ResponseEntity<?> getCompanyByName(@RequestParam(name = "value") String companyName) {
        Company company = companyService.getCompanyByName(companyName);
        return ResponseEntity.ok(company);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCompanyDetails(@PathVariable Long id, @RequestBody JsonPatch jsonPatch) {
        Company company = companyService.updateCompanyDetails(id, jsonPatch);

        return ResponseEntity.accepted().body(company);
    }
}
