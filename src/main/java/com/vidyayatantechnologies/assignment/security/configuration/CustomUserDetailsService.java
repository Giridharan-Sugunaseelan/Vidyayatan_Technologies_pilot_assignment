package com.vidyayatantechnologies.assignment.security.configuration;

import com.vidyayatantechnologies.assignment.entities.User;
import com.vidyayatantechnologies.assignment.exception.UserNotFoundException;
import com.vidyayatantechnologies.assignment.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!userRepository.existsByEmail(username)){
            throw new UserNotFoundException("No User found, Try Signing up.");
        }
        User user = this.userRepository.findByEmail(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach((role) -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
