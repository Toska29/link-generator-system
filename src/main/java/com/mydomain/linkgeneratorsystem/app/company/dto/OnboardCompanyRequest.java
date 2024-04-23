package com.mydomain.linkgeneratorsystem.app.company.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OnboardCompanyRequest {
    private String companyEmail;
    private String companyName;
    private String contactNumber;
    private String contactAddress;
}
