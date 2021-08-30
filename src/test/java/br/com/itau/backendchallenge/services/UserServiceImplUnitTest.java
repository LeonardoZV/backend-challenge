package br.com.itau.backendchallenge.services;

import br.com.itau.backendchallenge.models.Error;

import br.com.itau.backendchallenge.exceptions.InvalidPasswordException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImplUnitTest {

    private final UserServiceImpl userService;

    @Autowired
    public UserServiceImplUnitTest(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Test
    public void mustReturnTrueWhenPasswordIsValid() throws Exception {

        assertTrue(this.userService.isValidPassword("AbTp9!fok"));

    }

    @Test
    public void mustThrowErrorWhenPasswordIsNull() {

        InvalidPasswordException exception = assertThrows(InvalidPasswordException.class, () -> this.userService.isValidPassword(null));

        Optional<Error> error = exception.getErrors().stream().filter(e -> e.getType().equals("password-validation-001")).findFirst();

        assertTrue(error.isPresent());

        assertEquals("Campo password não preenchido.", error.get().getMessage());

    }

    @Test
    public void mustThrowErrorWhenPasswordIsEmpty() {

        InvalidPasswordException exception = assertThrows(InvalidPasswordException.class, () -> this.userService.isValidPassword(""));

        Optional<Error> error = exception.getErrors().stream().filter(e -> e.getType().equals("password-validation-001")).findFirst();

        assertTrue(error.isPresent());

        assertEquals("Campo password não preenchido.", error.get().getMessage());

    }

    @Test
    public void mustThrowErrorWhenPasswordHasLessThanNineCharacters() {

        InvalidPasswordException exception = assertThrows(InvalidPasswordException.class, () -> this.userService.isValidPassword("a"));

        Optional<Error> error = exception.getErrors().stream().filter(e -> e.getType().equals("password-validation-002")).findFirst();

        assertTrue(error.isPresent());

        assertEquals("Campo password deve conter nove ou mais caracteres.", error.get().getMessage());

    }

    @Test
    public void mustThrowErrorWhenPasswordHasNotAtLeastOneDigit() {

        InvalidPasswordException exception = assertThrows(InvalidPasswordException.class, () -> this.userService.isValidPassword("abcdefghi"));

        Optional<Error> error = exception.getErrors().stream().filter(e -> e.getType().equals("password-validation-003")).findFirst();

        assertTrue(error.isPresent());

        assertEquals("Campo password deve conter ao menos um dígito.", error.get().getMessage());

    }

    @Test
    public void mustThrowErrorWhenPasswordHasNotAtLeastOneLowercaseLetter() {

        InvalidPasswordException exception = assertThrows(InvalidPasswordException.class, () -> this.userService.isValidPassword("A"));

        Optional<Error> error = exception.getErrors().stream().filter(e -> e.getType().equals("password-validation-004")).findFirst();

        assertTrue(error.isPresent());

        assertEquals("Campo password deve conter ao menos uma letra minúscula.", error.get().getMessage());

    }

    @Test
    public void mustThrowErrorWhenPasswordHasNotAtLeastOneUppercaseLetter() {

        InvalidPasswordException exception = assertThrows(InvalidPasswordException.class, () -> this.userService.isValidPassword("b"));

        Optional<Error> error = exception.getErrors().stream().filter(e -> e.getType().equals("password-validation-005")).findFirst();

        assertTrue(error.isPresent());

        assertEquals("Campo password deve conter ao menos uma letra maiúscula.", error.get().getMessage());

    }

    @Test
    public void mustThrowErrorWhenPasswordHasNotAtLeastOneSpecialCharacter() {

        InvalidPasswordException exception = assertThrows(InvalidPasswordException.class, () -> this.userService.isValidPassword("c"));

        Optional<Error> error = exception.getErrors().stream().filter(e -> e.getType().equals("password-validation-006")).findFirst();

        assertTrue(error.isPresent());

        assertEquals("Campo password deve conter ao menos um caractere especial.", error.get().getMessage());

    }

    @Test
    public void mustThrowErrorWhenPasswordHasNotAllowedSpecialCharacter() {

        InvalidPasswordException exception = assertThrows(InvalidPasswordException.class, () -> this.userService.isValidPassword("."));

        Optional<Error> error = exception.getErrors().stream().filter(e -> e.getType().equals("password-validation-007")).findFirst();

        assertTrue(error.isPresent());

        assertEquals("Campo password deve conter apenas caraceteres válidos como: letras, digitos ou os caracteres especiais !@#$%^&*()-+", error.get().getMessage());

    }

    @Test
    public void mustThrowErrorWhenPasswordHasRepeatedCharacters() {

        InvalidPasswordException exception = assertThrows(InvalidPasswordException.class, () -> this.userService.isValidPassword("AbTp9!foA"));

        Optional<Error> error = exception.getErrors().stream().filter(e -> e.getType().equals("password-validation-008")).findFirst();

        assertTrue(error.isPresent());

        assertEquals("Campo password não deve conter caracteres repetidos.", error.get().getMessage());

    }

}
