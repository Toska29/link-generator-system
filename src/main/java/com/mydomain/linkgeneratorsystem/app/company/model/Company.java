package com.mydomain.linkgeneratorsystem.app.company.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Company {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(unique = true)
    private String companyName;
    private String companyEmail;
    private String contactNumber;
    private String contactAddress;
    @CreationTimestamp
    private LocalDateTime dateTimeSubscribed;

}
