package com.vidyayatantechnologies.assignment.dto;


import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDto {

    private Integer permission_id;

    private String name;

}
