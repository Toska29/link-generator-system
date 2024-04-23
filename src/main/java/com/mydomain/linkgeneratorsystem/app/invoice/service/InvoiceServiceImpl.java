package com.mydomain.linkgeneratorsystem.app.invoice.service;

import com.mydomain.linkgeneratorsystem.app.company.model.Company;
import com.mydomain.linkgeneratorsystem.app.company.service.CompanyService;
import com.mydomain.linkgeneratorsystem.app.customer.model.Customer;
import com.mydomain.linkgeneratorsystem.app.customer.service.CustomerService;
import com.mydomain.linkgeneratorsystem.app.invoice.dto.request.GenerateInvoiceDto;
import com.mydomain.linkgeneratorsystem.app.invoice.dto.response.InvoiceResponseDto;
import com.mydomain.linkgeneratorsystem.app.invoice.model.Invoice;
import com.mydomain.linkgeneratorsystem.app.invoice.model.Item;
import com.mydomain.linkgeneratorsystem.app.invoice.repository.InvoiceRepository;
import com.mydomain.linkgeneratorsystem.exception.LinkGeneratorException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService{
    private CompanyService companyService;
    private CustomerService customerService;
    private InvoiceRepository invoiceRepository;
    private ModelMapper modelMapper;

    @Override
    public InvoiceResponseDto generateInvoiceForCustomer(GenerateInvoiceDto invoiceDto) {
        Customer customer = customerService.findCustomerByEmail(invoiceDto.getCustomerEmail());
        Invoice invoice = new Invoice();

        invoiceDto.getItemDtoList().forEach(itemDto -> {
            Item item = modelMapper.map(itemDto, Item.class);
            item.setTotalAmount(itemDto.getCost() * itemDto.getQuantity());
            invoice.getItems().add(item);
        });

        invoice.setCustomer(customer);
        Double summation = invoice.getItems().stream().map(Item::getTotalAmount).reduce(0.0, Double::sum);
        BigDecimal  totalPrice= BigDecimal.valueOf(summation);

        invoice.setTotalPrice(totalPrice);
        Invoice savedInvoice = invoiceRepository.save(invoice);

        return buildInvoiceResponseDto(savedInvoice);
    }

    @Override
    public InvoiceResponseDto getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new LinkGeneratorException("invoice does not exist"));
        return buildInvoiceResponseDto(invoice);
    }

    @Override
    public List<InvoiceResponseDto> getInvoicesByCustomerCompany(String companyName) {
        Company company = companyService.getCompanyByName(companyName);
        List<Invoice> invoiceList = invoiceRepository.findInvoiceByCustomerCompany(company);

        return buildInvoiceResponseDtoList(invoiceList);
    }

    @Override
    public List<InvoiceResponseDto> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();

        return buildInvoiceResponseDtoList(invoices);
    }

    private InvoiceResponseDto buildInvoiceResponseDto(Invoice invoice) {
        InvoiceResponseDto invoiceResponseDto = modelMapper.map(invoice, InvoiceResponseDto.class);
        invoiceResponseDto.setInvoiceId(invoice.getId());
        invoiceResponseDto.setCustomerEmail(invoice.getCustomer().getCustomerEmail());
        invoiceResponseDto.setCustomerFullName(String.format("%s %s", invoice.getCustomer().getFirstName(), invoice.getCustomer().getLastName()));
        invoiceResponseDto.setCustomerPhoneNumber(invoice.getCustomer().getPhoneNumber());
        invoiceResponseDto.setCompanyName(invoice.getCustomer().getCompany().getCompanyName());
        invoiceResponseDto.setCompanyAddress(invoice.getCustomer().getCompany().getContactAddress());
        invoiceResponseDto.setCompanyContactNumber(invoice.getCustomer().getCompany().getContactNumber());

        return invoiceResponseDto;
    }

    private List<InvoiceResponseDto> buildInvoiceResponseDtoList(List<Invoice> invoiceList) {
        List<InvoiceResponseDto> invoiceResponseDtoList = new ArrayList<>();
        invoiceList.forEach(invoice -> invoiceResponseDtoList.add(buildInvoiceResponseDto(invoice)));

        return invoiceResponseDtoList;
    }
}
