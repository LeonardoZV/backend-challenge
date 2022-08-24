package br.com.itau.backendchallenge.exceptions;

import br.com.itau.backendchallenge.models.Error;
import lombok.Getter;

import java.util.List;

@Getter
public class InvalidPasswordException extends Exception {

    private final List<Error> errors;

    public InvalidPasswordException(List<Error> errors) {
        this.errors = errors;
    }

}
