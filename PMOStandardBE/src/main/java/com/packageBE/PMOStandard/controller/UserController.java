package com.packageBE.PMOStandard.controller;

import com.packageBE.PMOStandard.dto.UserDto;
import com.packageBE.PMOStandard.model.Activation;
import com.packageBE.PMOStandard.service.IBusinessUnitService;
import com.packageBE.PMOStandard.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController{

    private final IUserService userService;
    private final IBusinessUnitService unitService;
    public static final Logger logger = LoggerFactory.getLogger(RegisterController.class);


    public UserController(IUserService userService,
                          IBusinessUnitService unitService) {
        this.userService = userService;
        this.unitService = unitService;
    }

    @PostMapping()
    Long save(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }

    @PutMapping()
    Long update(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }

    @GetMapping("/{id}")
    UserDto getById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @GetMapping()
    List<UserDto> getAll() {
        return userService.getAll();
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }

    @RequestMapping("/login")
    public Principal user(Principal principal){
        return principal;
    }

    @PostMapping(value = "/forgetPassword")
    public ResponseEntity<?> forgetPassword(@RequestBody String email) {
        UserDto userDto;
        if ((userDto = userService.findByEmail(email)) == null) {
            logger.error("username does not exist " + email);
            return new ResponseEntity(
                    new Exception("user with username " + email + " does not exist "),
                    HttpStatus.CONFLICT);
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/plain");
        return new ResponseEntity<String>(userService.forgetPassword(email, userDto.getId()),responseHeaders, HttpStatus.OK);
    }

    @GetMapping("/activate")
    public @ResponseBody ResponseEntity<?> activation(@RequestParam("id") Integer id, @RequestParam("uuid") String uuid){
        return new ResponseEntity<Activation>(userService.activation(id, uuid), HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO){
        UserDto userDto;
        Long id = changePasswordDTO.getUserId();
        String password = changePasswordDTO.getPassword();
        if ((userDto = userService.getById(id)) == null){
            logger.error("username does not exist with this ID: " + id);
            return new ResponseEntity(
                    new Exception("user with id " + id + " does not exist "),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.changePassword(userDto, password),HttpStatus.OK);
    }

    public static class ChangePasswordDTO{
        private Long userId;
        private String password;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
