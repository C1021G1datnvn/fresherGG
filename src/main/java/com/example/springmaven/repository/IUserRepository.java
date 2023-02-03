package com.example.springmaven.repository;

import com.example.springmaven.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface IUserRepository extends JpaRepository<User, Long> {
    Page<User> findUserByNameUserContainingAndAddressUserContainingAndPhoneUserContaining(
            String name, String address, String phone, Pageable pageable);
}
