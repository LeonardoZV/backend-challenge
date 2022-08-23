package br.com.itau.backendchallenge.controllers;

import br.com.itau.backendchallenge.exceptions.InvalidPasswordException;
import br.com.itau.backendchallenge.models.ApiErrorResponse;
import br.com.itau.backendchallenge.models.ValidatePasswordRequest;
import br.com.itau.backendchallenge.services.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<ApiErrorResponse> handleInvalidPasswordException(InvalidPasswordException ex) {
        return new ResponseEntity<>(new ApiErrorResponse(ex.getErrors()), HttpStatus.BAD_REQUEST);
    }

}