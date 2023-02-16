package com.example.springmaven.controller;

import com.example.springmaven.dto.UserDto;
import com.example.springmaven.model.TypeUser;
import com.example.springmaven.model.User;
import com.example.springmaven.service.ITypeUserService;
import com.example.springmaven.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private IUserService iuserService;

    @Autowired
    private ITypeUserService iTypeUserService;

    @GetMapping("/listUser")
    public ResponseEntity<Page<User>> showListUser(@RequestParam(defaultValue = "0") int page){
        Page<User> users = iuserService.findListUser(PageRequest.of(page, 5));
        if(users.getTotalPages()<=page){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            Map<String, Object> response = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errorMap.put(error.getField(), error.getDefaultMessage());
            });
            response.put("error", errorMap);
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        } else {
            iuserService.createUser(userDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @GetMapping("/findUserId/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id){
        User user = iuserService.findByUser(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @DeleteMapping("/softDeleteUser/{id}")
    public ResponseEntity<User> softDeleteUser(@PathVariable Long id){
        User user = iuserService.findByUser(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            iuserService.softRemoveUser(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @DeleteMapping("/hardDeleteUser/{id}")
    public ResponseEntity<User> hardDeleteUser(@PathVariable Long id){
        User user = iuserService.findByUser(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            iuserService.hardDeleteUser(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @PatchMapping("/editUser/{id}")
    public ResponseEntity<?> editUser(@Valid @RequestBody UserDto userDto, @PathVariable Long id, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            Map<String, Object> response = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errorMap.put(error.getField(), error.getDefaultMessage());
            });
            response.put("error", errorMap);
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        iuserService.updateUser(id, userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/searchUser")
    public ResponseEntity<Page<User>> searchUser(@RequestParam String name, @RequestParam String address,
                                            @RequestParam String phone, @RequestParam(defaultValue = "0") int page) {
        Page<User> users = iuserService.searchUser(name, address, phone, PageRequest.of(page, 5));
        if (users == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }

    @GetMapping("/list-typeUser")
    public ResponseEntity<List<TypeUser>> findAllTypeUser() {
        List<TypeUser> typeUsers = iTypeUserService.findAllTypeUser();
        if (typeUsers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(typeUsers,HttpStatus.OK);
    }

    @GetMapping("/typeUser/{id}")
    public ResponseEntity<TypeUser> findTypeUserById(@PathVariable Long id) {
        TypeUser typeUser = iTypeUserService.findTypeUserById(id);
        if (typeUser == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(typeUser,HttpStatus.OK);
    }
}
