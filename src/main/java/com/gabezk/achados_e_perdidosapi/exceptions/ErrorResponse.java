package com.gabezk.achados_e_perdidosapi.exceptions;

import lombok.*;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private ZonedDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
