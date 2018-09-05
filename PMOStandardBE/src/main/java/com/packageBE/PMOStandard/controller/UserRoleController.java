package com.packageBE.PMOStandard.controller;

import com.packageBE.PMOStandard.service.IUserRoleService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    private final IUserRoleService roleService;

    public UserRoleController(IUserRoleService roleService){this.roleService = roleService;}

    @GetMapping()
    List<String> getAllUserRole(){return roleService.toList();}
}
