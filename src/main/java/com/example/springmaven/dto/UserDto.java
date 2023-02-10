package com.example.springmaven.dto;

import jakarta.validation.constraints.*;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;

public class UserDto implements Validator {

    private Long id;

    @NotBlank(message = "Không được để trống!")
    @Size(min = 5, max = 50)
    private String nameUser;

    @NotBlank(message = "Không được để trống!")
    @Size(min = 5, max = 50)
    private String addressUser;

    @Size(min = 10, max = 11)
    @Pattern(regexp="^[0-9]*$", message="Vui lòng nhập đúng số điện thoại!")
    private String phoneUser;
    private Date birthdayUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getAddressUser() {
        return addressUser;
    }

    public void setAddressUser(String addressUser) {
        this.addressUser = addressUser;
    }

    public String getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(String phoneUser) {
        this.phoneUser = phoneUser;
    }

    public Date getBirthdayUser() {
        return birthdayUser;
    }

    public void setBirthdayUser(Date birthdayUser) {
        this.birthdayUser = birthdayUser;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
