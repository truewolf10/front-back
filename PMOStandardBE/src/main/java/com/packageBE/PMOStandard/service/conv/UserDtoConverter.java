package com.packageBE.PMOStandard.service.conv;

import com.packageBE.PMOStandard.dto.UserDto;
import com.packageBE.PMOStandard.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setBusinessUnit(user.getBusinessUnit());
        userDto.setUserRole(user.getUserRole());

        return userDto;
    }

    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setBusinessUnit(userDto.getBusinessUnit());
        user.setUserRole(userDto.getUserRole());

        return user;
    }
}
