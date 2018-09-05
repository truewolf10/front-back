package com.packageBE.PMOStandard.service.impl;

import com.packageBE.PMOStandard.dto.UserDto;
import com.packageBE.PMOStandard.model.Activation;
import com.packageBE.PMOStandard.model.User;
import com.packageBE.PMOStandard.service.IUserService;
import com.packageBE.PMOStandard.service.PasswordEncoderFactory;
import com.packageBE.PMOStandard.service.conv.UserDtoConverter;
import com.packageBE.PMOStandard.repository.IActivationRepository;
import com.packageBE.PMOStandard.repository.IUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final UserDtoConverter userDtoConverter;
    private final MailService mailService;
    private final IActivationRepository activationRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(IUserRepository userRepository,
                       UserDtoConverter userDtoConverter, MailService mailService, IActivationRepository activationRepository) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
        this.mailService = mailService;
        this.activationRepository = activationRepository;
        this.passwordEncoder = PasswordEncoderFactory.create();
    }

    @Override
    public Long save(UserDto userDto) {
        User user = userDtoConverter.toEntity(userDto);
        return userRepository.save(user).getId();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto getById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find user with ID: " + id));

        UserDto userDto = userDtoConverter.toDto(user);

        return userDto;
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for(User user : users) {
            userDtos.add(userDtoConverter.toDto(user));
        }

        return userDtos;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final User user = userRepository.findByEmail(email);

        if(user == null) {
            throw new UsernameNotFoundException(email + " does not exist");
        }

        final Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getUserRole().toString()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }

    @Override
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return (user == null)? null : userDtoConverter.toDto(user);
    }

    @Override
    public String forgetPassword(String email, Long id) {
        User user = userDtoConverter.toEntity(findByEmail(email));
        Activation activationAlreadyExist;
        String uuid = UUID.randomUUID().toString();

        try {
            activationAlreadyExist = getByIdActivation(user.getId());
            activationAlreadyExist.setGeneratedId(uuid);
            activationRepository.save(activationAlreadyExist);
        }
        catch (Exception ex){
            Activation activation = new Activation();
            user.setActivation(activation);
            activation.setUser(user);
            activation.setGeneratedId(uuid);
            userRepository.save(user);
//            activationRepository.save(activation);
        }


        mailService.sendActivationMail(email,id.intValue(),uuid);
        return "Everything went well";
    }

    @Override
    public Activation activation(Integer id, String uuid) {
//        User user = userDtoConverter.toEntity(getById(Long.valueOf(id)));
        Activation activation = getByIdActivation(id.longValue());
        if (activation.getGeneratedId().equals(uuid))
            activation.setActivated(true);

        activationRepository.save(activation);
        return activation;
    }


    private Activation getByIdActivation(Long id){
        User user = userDtoConverter.toEntity(getById(id));
        Activation activation = activationRepository.findByUser(user);
        if (activation == null)
            throw new EntityNotFoundException("Cannot find user with Id: " + id);
        return activation;
    }

    public UserDto changePassword(UserDto userDto, String password){
        User user = userDtoConverter.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return userDtoConverter.toDto(user);
    }

}