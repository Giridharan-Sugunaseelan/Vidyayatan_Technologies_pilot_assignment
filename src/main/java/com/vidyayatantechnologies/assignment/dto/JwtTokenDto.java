package com.vidyayatantechnologies.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtTokenDto {

    private String type = "Bearer ";

    private String token;

    public JwtTokenDto(String token){
        this.token = token;
    }

}
