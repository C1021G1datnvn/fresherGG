package com.example.springmaven.service;

import com.example.springmaven.model.Customer;
import org.springframework.data.jpa.domain.Specification;

public interface ICustomerSpecification {

    static Specification<Customer> likeFirstName(String firstName) {
        if (firstName == null) {
            return null;
        }
        return (root, query, cb) -> {
            return cb.like(root.get("firstName"), "%" + firstName + "%");
        };
    }

    static Specification<Customer> likeLastName(String lastName) {
        if (lastName == null) {
            return null;
        }
        return (root, query, cb) -> {
            return cb.like(root.get("lastName"), "%" + lastName + "%");
        };
    }

    static Specification<Customer> equalEmail(String email) {
        if (email == null) {
            return null;
        }
        return (root, query, cb) -> {
            return cb.like(root.get("email"), email);
        };
    }

}
