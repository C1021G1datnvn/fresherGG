package com.example.springmaven.service;

import com.example.springmaven.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    Page<User> findListUser(Pageable pageable);
    void addUser(User user);
    void removeUser(Long id);
    void updateUser(User user);
    User findByUser(Long id);
    Page<User> searchUser(String name,String address, String phone, Pageable pageable);
}
