package com.mydomain.linkgeneratorsystem.app.invoice.repository;

import com.mydomain.linkgeneratorsystem.app.company.model.Company;
import com.mydomain.linkgeneratorsystem.app.company.repository.CompanyRepository;
import com.mydomain.linkgeneratorsystem.app.customer.model.Customer;
import com.mydomain.linkgeneratorsystem.app.customer.repository.CustomerRepository;
import com.mydomain.linkgeneratorsystem.app.invoice.model.Invoice;
import com.mydomain.linkgeneratorsystem.app.invoice.model.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles(value = "test")
class InvoiceRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void findInvoiceByCustomerCompanyTest() {
        //given

        Company company = new Company();
        company.setCompanyEmail("toskabenedict@gmail.com");
        company.setCompanyName("Prizeat");
        company.setContactNumber("08033010000");
        company.setContactAddress("371, Herbert Macaulay, Yaba, Lagos");

        Company savedCompany = companyRepository.save(company);

        Customer customer = new Customer();
        customer.setCompany(savedCompany);
        customer.setCustomerEmail("ben@gmail.com");
        customer.setFirstName("Ben");
        customer.setLastName("Toska");

        Customer savedCustomer = customerRepository.save(customer);

        Item item1 = new Item();
        item1.setDescription("computer");
        item1.setCost(100000d);
        item1.setQuantity(1);

        Item item2 = new Item();
        item1.setDescription("mouse");
        item1.setCost(5000d);
        item1.setQuantity(2);

        Invoice invoice = new Invoice();
        invoice.setCustomer(savedCustomer);
        invoice.setItems(List.of(item1, item2));
        invoice.setTotalPrice(BigDecimal.valueOf(110000));

        invoiceRepository.save(invoice);

        //when
        List<Invoice> invoiceList = invoiceRepository.findInvoiceByCustomerCompany(savedCompany);

        //then
        assertThat(invoiceList.size()).isGreaterThan(0);
    }
}