package com.microservice.auth.repository;

import java.util.Optional;

import com.microservice.auth.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AccountRepository
extends JpaRepository<Account,Integer>{

Optional<Account>
findByEmail(
String email
);

}