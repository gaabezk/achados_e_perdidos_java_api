package com.gabezk.achados_e_perdidos_java_api.exceptions;

import lombok.*;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class CustomErrorResponse {

    private ZonedDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
