package com.vidyayatantechnologies.assignment.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {
    private LocalDateTime dateTime;
    private String message;
    private String description;
}
