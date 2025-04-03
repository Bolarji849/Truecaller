package com.byteBuilders.TrueCaller.exception;

public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(String message) {
        super(message);
    }
}
