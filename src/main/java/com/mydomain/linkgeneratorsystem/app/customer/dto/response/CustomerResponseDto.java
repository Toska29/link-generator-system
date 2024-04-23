package com.mydomain.linkgeneratorsystem.app.customer.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomerResponseDto {
    private Long id;
    private String customerEmail;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Long companyId;
    private String companyName;
    private LocalDateTime dateTimeRegistered;
}
