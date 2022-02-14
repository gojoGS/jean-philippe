package com.example.demo.security.user.password.encryption;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Sha256EncryptionService implements PasswordEncryptionService {
    @Override
    public String encrypt(String raw) {
        return Arrays.toString(DigestUtils.sha256(raw));
    }
}
