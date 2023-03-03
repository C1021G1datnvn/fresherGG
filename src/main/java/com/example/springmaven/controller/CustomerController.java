package com.example.springmaven.controller;

import com.example.springmaven.model.Customer;
import com.example.springmaven.repository.ICustomerSpecificationRepo;
import com.example.springmaven.service.ICustomerSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    ICustomerSpecificationRepo specificationRepo;

    @GetMapping("/specification")
    public List<Customer> getCustomers(String firstName, String lastName, String email) {
        Specification<Customer> spec = Specification.where(null);
        if (firstName != null && !firstName.isEmpty()) {
            spec = spec.and(ICustomerSpecification.likeFirstName(firstName));
        }
        if (lastName != null && !lastName.isEmpty()) {
            spec = spec.and(ICustomerSpecification.likeLastName(lastName));
        }
        if (email != null) {
            spec = spec.and(ICustomerSpecification. equalEmail(email));
        }
        return specificationRepo.findAll(spec);
    }
}
