package br.com.itau.backendchallenge.controllers;

import br.com.itau.backendchallenge.exceptions.InvalidPasswordException;
import br.com.itau.backendchallenge.models.Error;
import br.com.itau.backendchallenge.services.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserControllerUnitTest {

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    @Test
    void whenReceiveAnyString_thenCallIsValidPasswordMethod() throws Exception {

        this.userController.validatePassword("AbTp9!fok");

        verify(this.userService).isValidPassword(any(String.class));

    }

    @Test
    void whenInvalidPasswordExceptionIsThrown_thenReturns400() {

        List<Error> errorList = new ArrayList<>();

        Error error = new Error("password-validation-001", "Campo password não preenchido.");

        errorList.add(error);

        ResponseEntity<List<Error>> expectedResponseEntity = new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);

        ResponseEntity<List<Error>> actualResponseEntity = this.userController.handleInvalidPasswordException(new InvalidPasswordException(errorList));

        assertEquals(expectedResponseEntity.getStatusCode(), actualResponseEntity.getStatusCode());

    }

}
