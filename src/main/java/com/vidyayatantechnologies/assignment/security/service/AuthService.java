package com.vidyayatantechnologies.assignment.security.service;

import com.vidyayatantechnologies.assignment.dto.JwtTokenDto;
import com.vidyayatantechnologies.assignment.dto.LoginDto;
import com.vidyayatantechnologies.assignment.dto.RegisterDto;
import com.vidyayatantechnologies.assignment.dto.UserDto;
import com.vidyayatantechnologies.assignment.entities.Role;
import com.vidyayatantechnologies.assignment.entities.User;
import com.vidyayatantechnologies.assignment.exception.ExistingUserException;
import com.vidyayatantechnologies.assignment.repository.RoleRepository;
import com.vidyayatantechnologies.assignment.repository.UserRepository;
import com.vidyayatantechnologies.assignment.security.configuration.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.PasswordAuthentication;

@Service
@AllArgsConstructor
public class AuthService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    private JwtTokenProvider tokenProvider;

    private AuthenticationManager authenticationManager;

    private ModelMapper modelMapper;

    public UserDto registerUser(RegisterDto dto){
        if(this.userRepository.existsByEmail(dto.getEmail())){
            throw new ExistingUserException("User already exists!");
        }
        User user = this.modelMapper.map(dto, User.class);
        Role role = this.roleRepository.findByName(dto.getRole());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.getRoles().add(role);
        System.out.println(user);
        User registeredUser = this.userRepository.save(user);
        UserDto userDto = this.modelMapper.map(registeredUser, UserDto.class);
        userDto.setRole(role.getName());
        return userDto;
    }

    public JwtTokenDto loginUser(LoginDto dto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);
        return new JwtTokenDto(token);
    }
}
