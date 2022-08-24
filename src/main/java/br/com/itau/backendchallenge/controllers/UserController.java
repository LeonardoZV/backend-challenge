package br.com.itau.backendchallenge.controllers;

import br.com.itau.backendchallenge.exceptions.InvalidPasswordException;
import br.com.itau.backendchallenge.models.Error;
import br.com.itau.backendchallenge.models.ValidatePasswordRequest;
import br.com.itau.backendchallenge.services.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/user/password/validate")
    public void validatePassword(@RequestBody ValidatePasswordRequest validatePasswordRequest) throws InvalidPasswordException {
        this.userService.isValidPassword(validatePasswordRequest.getPassword());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<List<Error>> handleInvalidPasswordException(InvalidPasswordException ex) {
        return new ResponseEntity<>(ex.getErrors(), HttpStatus.BAD_REQUEST);
    }

}