package com.example.demo.web.rest;
import com.example.demo.facade.dto.PasswordChangeDTO;

import com.example.demo.service.exceptions.BusinessException;
import com.example.demo.service.exceptions.enumeration.ErrorConstants;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.web.rest.vm.ManagedUserVM;
import org.apache.commons.lang3.StringUtils;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AccountResource {


    private final UserServiceImpl userServiceImpl;

    public AccountResource(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@Valid @RequestBody ManagedUserVM managedUserVM){
        if (isPasswordLengthInvalid(managedUserVM.getPassword())) {
            throw BusinessException.by(ErrorConstants.INVALID_PASSWORD).get();
        }
        userServiceImpl.registerUser(managedUserVM, managedUserVM.getPassword());
    }

    @PostMapping(path = "/account/change-password")
    public void changePassword(@RequestBody PasswordChangeDTO passwordChangeDto) {
        if (isPasswordLengthInvalid(passwordChangeDto.getNewPassword())) {
            throw BusinessException.by(ErrorConstants.INVALID_PASSWORD).get();
        }
        userServiceImpl.changePassword(passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());
    }

    private static boolean isPasswordLengthInvalid(String password) {
        return (
                StringUtils.isEmpty(password) ||
                        password.length() < ManagedUserVM.PASSWORD_MIN_LENGTH ||
                        password.length() > ManagedUserVM.PASSWORD_MAX_LENGTH
        );
    }
}