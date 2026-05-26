package com.microservice.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="customer_profile")
@Getter
@Setter
public class CustomerProfile {

    @Id
    private Integer accountId;

    @OneToOne
    @MapsId
    @JoinColumn(name="account_id")
    private Account account;

    private String firstName;

    private String lastName;

    private String phone;

}