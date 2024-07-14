package com.vidyayatantechnologies.assignment.exception;

import com.vidyayatantechnologies.assignment.error.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler({PermissionDeniedException.class})
    public ResponseEntity<Error> PermissionDeniedExceptionHandler(WebRequest request, Exception exception){
        Error error = new Error(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatusCode.valueOf(403));
    }

    @ExceptionHandler({UserNotFoundException.class, BookNotFoundException.class, PermissionNotFoundException.class, RoleNotFoundException.class})
    public ResponseEntity<Error> NotFoundExceptionHandler(WebRequest request, Exception exception){
        Error error = new Error(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ExistingUserException.class})
    public ResponseEntity<Error> ExistingUserException(WebRequest request, Exception exception){
        Error error = new Error(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}

