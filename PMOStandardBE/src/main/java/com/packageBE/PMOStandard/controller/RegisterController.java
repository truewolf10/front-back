package com.packageBE.PMOStandard.controller;

import com.packageBE.PMOStandard.dto.UserDto;
import com.packageBE.PMOStandard.service.IUserService;
import com.packageBE.PMOStandard.service.impl.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class RegisterController {
    private final RegisterService registerService;
    public static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    private final IUserService userService;

    public RegisterController(RegisterService registerService, IUserService userService) {
        this.registerService = registerService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        if (userService.findByEmail(userDto.getEmail()) != null) {
            logger.error("username Already exist " + userDto.getEmail());
            return new ResponseEntity(
                    new Exception("user with username " + userDto.getEmail() + " already exist "),
                    HttpStatus.CONFLICT);
        }

        return new ResponseEntity<UserDto>(registerService.register(userDto), HttpStatus.CREATED);
    }
}
