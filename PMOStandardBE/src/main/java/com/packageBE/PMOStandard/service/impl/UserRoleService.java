package com.packageBE.PMOStandard.service.impl;

import com.packageBE.PMOStandard.model.enumeration.UserRole;
import com.packageBE.PMOStandard.service.IUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserRoleService implements IUserRoleService {
    @Override
    public List<String> toList(){
        return Stream.of(UserRole.values())
                .map(UserRole::name)
                .collect(Collectors.toList());
    }
}

