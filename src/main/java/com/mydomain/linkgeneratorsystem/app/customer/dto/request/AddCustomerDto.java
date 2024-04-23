package com.mydomain.linkgeneratorsystem.app.customer.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddCustomerDto {
    private String companyName;
    private String customerEmail;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
