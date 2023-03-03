package com.example.springmaven.repository;

import com.example.springmaven.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ICustomerSpecificationRepo extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
}
