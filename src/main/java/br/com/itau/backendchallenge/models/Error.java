package br.com.itau.backendchallenge.models;

import java.io.Serializable;

public class Error implements Serializable {

    private String type;
    private String message;

    public Error(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

}
