package com.example.springmaven.service.impl;

import com.example.springmaven.model.TypeUser;
import com.example.springmaven.repository.ITypeUser;
import com.example.springmaven.service.ITypeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeUserService implements ITypeUserService {

    @Autowired
    private ITypeUser iTypeUser;

    @Override
    public List<TypeUser> findAllTypeUser() {
        return iTypeUser.findAllTypeUser();
    }

    @Override
    public TypeUser findTypeUserById(Long id) {
        return iTypeUser.findTypeUserById(id);
    }
}
