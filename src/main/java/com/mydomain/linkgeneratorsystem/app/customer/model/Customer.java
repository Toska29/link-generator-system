package com.mydomain.linkgeneratorsystem.app.customer.model;

import com.mydomain.linkgeneratorsystem.app.company.model.Company;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String customerEmail;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Company company;

    @CreationTimestamp
    private LocalDateTime dateTimeRegistered;
}
