package com.gabezk.achados_e_perdidos_java_api.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomErrorException extends Exception {

    private String message;

    public CustomErrorException(String message) {
        super();
        this.message = message;
    }

}
