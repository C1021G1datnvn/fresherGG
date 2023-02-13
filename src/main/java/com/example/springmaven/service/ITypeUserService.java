package com.example.springmaven.service;

import com.example.springmaven.model.TypeUser;

import java.util.List;

public interface ITypeUserService {
    List<TypeUser> findAllTypeUser();

    TypeUser findTypeUserById(Long id);
}
