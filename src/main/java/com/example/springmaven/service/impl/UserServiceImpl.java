package com.example.springmaven.service.impl;

import com.example.springmaven.dto.UserDto;
import com.example.springmaven.model.User;
import com.example.springmaven.repository.IUserRepository;
import com.example.springmaven.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public Page<User> findListUser(Pageable pageable) {
        return userRepository.findAllUser(pageable);
    }

    @Override
    public void createUser(UserDto userDto) {
        userRepository.createUser(userDto.getNameUser(), userDto.getAddressUser(), userDto.getPhoneUser(), userDto.getBirthdayUser(),
                userDto.getTypeUser(), true);
    }

    @Override
    public void softRemoveUser(Long id) {
        userRepository.deleteUserById(id);
    }

    @Override
    public void hardDeleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(Long id,UserDto userDto) {
        userRepository.updateUser(userDto.getNameUser(), userDto.getAddressUser(), userDto.getPhoneUser(), userDto.getBirthdayUser(),
                true, userDto.getTypeUser(), id);
    }


    @Override
    public User findByUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Page<User> searchUser(String name, String address, String phone, Pageable pageable) {
        return userRepository.findUserByNameUserContainingAndAddressUserContainingAndPhoneUserContaining(
                name, address, phone, pageable);
    }
}
