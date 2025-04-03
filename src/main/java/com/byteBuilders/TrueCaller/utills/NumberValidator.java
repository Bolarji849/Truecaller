package com.byteBuilders.TrueCaller.utills;

public class NumberValidator {
    private void validatePhoneNumber(String phoneNumber) {
        if (!phoneNumber.matches("^\\+?[0-9\\s-]{10,}$")) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
}
}
