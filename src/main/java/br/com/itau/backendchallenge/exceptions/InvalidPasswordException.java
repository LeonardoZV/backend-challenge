package br.com.itau.backendchallenge.exceptions;

import br.com.itau.backendchallenge.models.Error;

import java.util.List;

public class InvalidPasswordException extends Exception {

    private final List<Error> errors;

    public InvalidPasswordException(List<Error> errors) {
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return errors;
    }

}
