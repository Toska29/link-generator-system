package com.mydomain.linkgeneratorsystem.app.invoice.controller;

import com.mydomain.linkgeneratorsystem.app.invoice.dto.request.GenerateInvoiceDto;
import com.mydomain.linkgeneratorsystem.app.invoice.dto.response.InvoiceResponseDto;
import com.mydomain.linkgeneratorsystem.app.invoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateInvoiceForCustomer(@RequestBody GenerateInvoiceDto invoiceDto) {
        InvoiceResponseDto invoiceResponseDto = invoiceService.generateInvoiceForCustomer(invoiceDto);

        return new ResponseEntity<>(invoiceResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInvoiceById(@PathVariable Long id) {
        InvoiceResponseDto invoiceResponseDto = invoiceService.getInvoiceById(id);

        return ResponseEntity.ok(invoiceResponseDto);
    }

    @GetMapping("/company")
    public ResponseEntity<?> getInvoicesByCustomerCompany(@RequestParam(name = "value") String companyName) {
        List<InvoiceResponseDto> invoiceResponseDtoList = invoiceService.getInvoicesByCustomerCompany(companyName);

        return ResponseEntity.ok(invoiceResponseDtoList);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllGeneratedInvoices() {
        List<InvoiceResponseDto> allInvoices = invoiceService.getAllInvoices();

        return ResponseEntity.ok(allInvoices);
    }
}
