package com.packageBE.PMOStandard.service;

import com.packageBE.PMOStandard.dto.UserDto;
import com.packageBE.PMOStandard.model.Activation;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    Long save(UserDto userDto);

    void delete(Long id);

    UserDto getById(Long id);

    List<UserDto> getAll();

    UserDto findByEmail(String email);

    String forgetPassword(String email, Long id);

    Activation activation(Integer id, String uuid);

    UserDto changePassword(UserDto userDto, String password);
}
