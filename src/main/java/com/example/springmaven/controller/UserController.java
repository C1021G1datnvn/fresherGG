package com.example.springmaven.controller;

import com.example.springmaven.model.User;
import com.example.springmaven.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private IUserService iuserService;

    @GetMapping("/listUser")
    public ResponseEntity<Page<User>> showListUser(@RequestParam(defaultValue = "0") int page){
        Page<User> users = iuserService.findListUser(PageRequest.of(page, 5));
        if (users == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user){
        iuserService.addUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
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

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        User user = iuserService.findByUser(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            iuserService.removeUser(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @PatchMapping("/editUser/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User user) {
        User userEdit = iuserService.findByUser(id);
        if (userEdit == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            userEdit.setNameUser(user.getNameUser());
            userEdit.setAddressUser(user.getAddressUser());
            userEdit.setPhoneUser(user.getPhoneUser());
            userEdit.setBirthdayUser(user.getBirthdayUser());
            iuserService.updateUser(userEdit);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
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

}
