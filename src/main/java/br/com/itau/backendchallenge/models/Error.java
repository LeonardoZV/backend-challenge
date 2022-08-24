package br.com.itau.backendchallenge.models;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class Error implements Serializable {

    private String type;
    private String message;

    public Error(String type, String message) {
        this.type = type;
        this.message = message;
    }

}
