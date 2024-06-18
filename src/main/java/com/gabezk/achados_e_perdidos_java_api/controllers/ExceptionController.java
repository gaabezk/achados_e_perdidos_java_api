package com.gabezk.achados_e_perdidos_java_api.controllers;


import com.gabezk.achados_e_perdidos_java_api.exceptions.CustomErrorResponse;
import com.gabezk.achados_e_perdidos_java_api.exceptions.CustomErrorException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(CustomErrorException.class)
    public ResponseEntity<CustomErrorResponse> GeneralException(CustomErrorException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(
                ZonedDateTime.now(),
                status.value(),
                status.toString(),
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(customErrorResponse);
    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<CustomErrorResponse> GeneralException(SQLIntegrityConstraintViolationException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(
                ZonedDateTime.now(),
                status.value(),
                status.toString(),
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(customErrorResponse);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<CustomErrorResponse> GeneralException(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(
                ZonedDateTime.now(),
                status.value(),
                status.toString(),
                (e.getFieldError().getField() + " " + e.getFieldError().getDefaultMessage()),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(customErrorResponse);
    }
}