package com.packageBE.PMOStandard.service.impl;

import com.packageBE.PMOStandard.dto.UserDto;
import com.packageBE.PMOStandard.model.enumeration.UserRole;
import com.packageBE.PMOStandard.service.IRegisterService;
import com.packageBE.PMOStandard.service.PasswordEncoderFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService implements IRegisterService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegisterService(UserService userService) {
        this.userService = userService;
        this.passwordEncoder = PasswordEncoderFactory.create();
    }

    @Override
    public UserDto register(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setUserRole(UserRole.USER);
        userService.save(userDto);
        return userDto;
    }
}