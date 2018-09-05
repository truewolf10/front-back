package com.packageBE.PMOStandard.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderFactory {
    public static PasswordEncoder create() {
        return new BCryptPasswordEncoder();
    }
}
