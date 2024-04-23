package com.mydomain.linkgeneratorsystem.app.invoice.model;

import com.mydomain.linkgeneratorsystem.app.customer.model.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Item> items = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.MERGE)
    private Customer customer;

    private BigDecimal totalPrice;

    @CreationTimestamp
    private LocalDateTime dateTimeGenerated;
}
