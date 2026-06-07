package com.microservice.auth.customer;

import com.microservice.auth.account.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer_profile")
@Getter
@Setter
public class CustomerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "first_name", nullable = false, length = 80)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 80)
    private String lastName;

    @Column(length = 20)
    private String phone;

    public CustomerProfile(){}
}