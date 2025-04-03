package com.byteBuilders.TrueCaller.exception;

public class UserAlreadyLoggedInException extends RuntimeException {
    public UserAlreadyLoggedInException(String message) {
        super(message);
    }
}
