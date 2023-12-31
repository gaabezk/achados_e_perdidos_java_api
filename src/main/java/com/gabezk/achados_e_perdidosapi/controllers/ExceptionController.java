package com.gabezk.achados_e_perdidosapi.controllers;

import com.gabezk.achados_e_perdidosapi.exceptions.ErrorResponse;
import com.gabezk.achados_e_perdidosapi.exceptions.ErrorException;
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
    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<ErrorResponse> GeneralException(ErrorException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(
                ZonedDateTime.now(),
                status.value(),
                status.toString(),
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(errorResponse);
    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> GeneralException(SQLIntegrityConstraintViolationException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse errorResponse = new ErrorResponse(
                ZonedDateTime.now(),
                status.value(),
                status.toString(),
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> GeneralException(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(
                ZonedDateTime.now(),
                status.value(),
                status.toString(),
                (e.getFieldError().getField() + " " + e.getFieldError().getDefaultMessage()),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(errorResponse);
    }
}