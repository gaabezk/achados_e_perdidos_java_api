package com.gabezk.achados_e_perdidosapi.exceptions;

public class ErrorException extends Exception {

    private String message;

    public ErrorException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
