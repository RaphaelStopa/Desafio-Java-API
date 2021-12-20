package com.example.demo.service.exceptions;


import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class InvalidPasswordException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public InvalidPasswordException() {
        super(URI.create("/invalid-password"), "Incorrect password", Status.BAD_REQUEST);
    }
}
