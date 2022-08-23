package br.com.itau.backendchallenge.models;

import java.util.List;

public class ApiErrorResponse {

    private final List<Error> errors;

    public ApiErrorResponse(List<Error> errors) {
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return errors;
    }

}
