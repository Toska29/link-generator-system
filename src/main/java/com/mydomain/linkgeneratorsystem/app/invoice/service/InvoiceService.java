package com.mydomain.linkgeneratorsystem.app.invoice.service;

import com.mydomain.linkgeneratorsystem.app.invoice.dto.request.GenerateInvoiceDto;
import com.mydomain.linkgeneratorsystem.app.invoice.dto.response.InvoiceResponseDto;

import java.util.List;

public interface InvoiceService {
    InvoiceResponseDto generateInvoiceForCustomer(GenerateInvoiceDto invoiceDto);

    InvoiceResponseDto getInvoiceById(Long id);

    List<InvoiceResponseDto> getInvoicesByCustomerCompany(String companyName);

    List<InvoiceResponseDto> getAllInvoices();
}
