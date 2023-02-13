package com.example.springmaven.service;

import com.example.springmaven.dto.UserDto;
import com.example.springmaven.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    Page<User> findListUser(Pageable pageable);
    void createUser(UserDto userDto);
    void softRemoveUser(Long id);
    void hardDeleteUser(Long id);
    void updateUser(Long id, UserDto userDto);
    User findByUser(Long id);
    Page<User> searchUser(String name,String address, String phone, Pageable pageable);
}
