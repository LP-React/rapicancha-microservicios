package com.microservice.auth.owner;

import com.microservice.auth.account.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "owner_profile")
@Getter
@Setter
public class OwnerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "first_name", nullable = false, length = 80)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 80)
    private String lastName;

    @Column(name = "national_id", nullable = false, length = 20)
    private String nationalId;

    @Column(length = 20)
    private String phone;

    public OwnerProfile(){}
}