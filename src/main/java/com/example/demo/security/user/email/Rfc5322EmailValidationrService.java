package com.example.demo.security.user.email;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

// TODO write unit test
// TOPIC Regular Expression by RFC 5322 for Email Validation
@Service
public class Rfc5322EmailValidationrService implements EmailValidationrService {

    private static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    @Override
    public boolean isValidEmail(String email) {
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return patternMatches(email, regexPattern);
    }
}
