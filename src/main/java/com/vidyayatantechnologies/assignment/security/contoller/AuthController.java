package com.vidyayatantechnologies.assignment.security.contoller;

import com.vidyayatantechnologies.assignment.dto.JwtTokenDto;
import com.vidyayatantechnologies.assignment.dto.LoginDto;
import com.vidyayatantechnologies.assignment.dto.RegisterDto;
import com.vidyayatantechnologies.assignment.dto.UserDto;
import com.vidyayatantechnologies.assignment.security.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody RegisterDto dto){
        UserDto userDto = this.authService.registerUser(dto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> loginUser(@RequestBody LoginDto dto){
        JwtTokenDto jwtTokenDto = this.authService.loginUser(dto);
        return new ResponseEntity<>(jwtTokenDto, HttpStatus.OK);
    }

}
