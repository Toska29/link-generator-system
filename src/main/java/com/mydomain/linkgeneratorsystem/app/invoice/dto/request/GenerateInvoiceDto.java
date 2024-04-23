package com.mydomain.linkgeneratorsystem.app.invoice.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GenerateInvoiceDto {
    private String customerEmail;
    private List<ItemDto> itemDtoList;
}
