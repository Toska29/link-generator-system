package com.mydomain.linkgeneratorsystem.app.invoice.dto.response;

import com.mydomain.linkgeneratorsystem.app.invoice.model.Item;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class InvoiceResponseDto {
    private Long invoiceId;
    private String companyName;
    private String companyAddress;
    private String companyContactNumber;

    private String customerEmail;
    private String customerFullName;
    private String customerPhoneNumber;

    private List<Item> items;
    private BigDecimal totalPrice;
    private LocalDateTime dateTimeGenerated;
}
