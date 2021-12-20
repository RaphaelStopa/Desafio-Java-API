package com.example.demo.service.exceptions;

public class LoginAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public LoginAlreadyUsedException() {
        super(URIErrorConstants.LOGIN_ALREADY_USED_TYPE, "Login name already used!", "userManagement", "user exists");
    }
}
