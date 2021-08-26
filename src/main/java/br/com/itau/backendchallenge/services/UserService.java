package br.com.itau.backendchallenge.services;

import br.com.itau.backendchallenge.exceptions.InvalidPasswordException;

public interface UserService {

    boolean isValidPassword(String password) throws InvalidPasswordException;

}
