package com.example.springmaven.service.impl;

import com.example.springmaven.dto.UserDto;
import com.example.springmaven.model.User;
import com.example.springmaven.repository.IUserRepository;
import com.example.springmaven.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    @Transactional
    public Page<User> findListUser(Pageable pageable) {
        return userRepository.findAllUser(pageable);
    }

    @Override
    @Transactional
    public void createUser(UserDto userDto) {
        userRepository.createUser(userDto.getNameUser(), userDto.getAddressUser(), userDto.getPhoneUser(),
                userDto.getBirthdayUser(),
                userDto.getTypeUser(), true);
    }

    @Override
    @Transactional
    public void softRemoveUser(Long id) {
        userRepository.deleteUserById(id);
    }

    @Override
    @Transactional
    public void hardDeleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateUser(Long id,UserDto userDto) {
        userRepository.updateUser(userDto.getNameUser(), userDto.getAddressUser(), userDto.getPhoneUser(),
                userDto.getBirthdayUser(),
                true, userDto.getTypeUser(), id);
    }

    @Override
    @Transactional
    public User findByUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Page<User> searchUserContaining(String name, String address, String phone, Pageable pageable) {
        return userRepository.searchUserContaining(
                name, address, phone, pageable);
    }
}
