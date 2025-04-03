package com.byteBuilders.TrueCaller.utills;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityValidation {
    public static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }
    public static boolean validatePassword(String password, String hashedPassword) {
        return passwordEncoder.matches(password, hashedPassword);
    }
    public static boolean validateEmail(String email) {
        return email.contains("@");
    }
}
