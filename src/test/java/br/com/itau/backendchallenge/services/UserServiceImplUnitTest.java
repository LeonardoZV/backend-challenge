package br.com.itau.backendchallenge.services;

import br.com.itau.backendchallenge.exceptions.InvalidPasswordException;
import br.com.itau.backendchallenge.models.Error;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplUnitTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void whenPasswordIsValid_thenReturnTrue() throws Exception {

        assertTrue(this.userService.isValidPassword("AbTp9!fok"));

    }

    @Test
    void whenPasswordIsNull_thenThrowError() {

        InvalidPasswordException exception = assertThrows(InvalidPasswordException.class, () -> this.userService.isValidPassword(null));

        Optional<Error> error = exception.getErrors().stream().filter(e -> e.getType().equals("password-validation-001")).findFirst();

        assertTrue(error.isPresent());

        assertEquals("Campo password deve ser preenchido.", error.get().getMessage());

    }

    @Test
    void whenPasswordIsEmpty_thenThrowError() {

        InvalidPasswordException exception = assertThrows(InvalidPasswordException.class, () -> this.userService.isValidPassword(""));

        Optional<Error> error = exception.getErrors().stream().filter(e -> e.getType().equals("password-validation-001")).findFirst();

        assertTrue(error.isPresent());

        assertEquals("Campo password deve ser preenchido.", error.get().getMessage());

    }

    @Test
    void whenPasswordHasLessThanNineCharacters_thenThrowError() {

        InvalidPasswordException exception = assertThrows(InvalidPasswordException.class, () -> this.userService.isValidPassword("a"));

        Optional<Error> error = exception.getErrors().stream().filter(e -> e.getType().equals("password-validation-002")).findFirst();

        assertTrue(error.isPresent());

        assertEquals("Campo password deve conter nove ou mais caracteres.", error.get().getMessage());

    }

    @Test
    void whenPasswordHasNotAtLeastOneDigit_thenThrowError() {

        InvalidPasswordException exception = assertThrows(InvalidPasswordException.class, () -> this.userService.isValidPassword("abcdefghi"));

        Optional<Error> error = exception.getErrors().stream().filter(e -> e.getType().equals("password-validation-003")).findFirst();

        assertTrue(error.isPresent());

        assertEquals("Campo password deve conter ao menos um dígito.", error.get().getMessage());

    }

    @Test
    void whenPasswordHasNotAtLeastOneLowercaseLetter_thenThrowError() {

        InvalidPasswordException exception = assertThrows(InvalidPasswordException.class, () -> this.userService.isValidPassword("A"));

        Optional<Error> error = exception.getErrors().stream().filter(e -> e.getType().equals("password-validation-004")).findFirst();

        assertTrue(error.isPresent());

        assertEquals("Campo password deve conter ao menos uma letra minúscula.", error.get().getMessage());

    }

    @Test
    void whenPasswordHasNotAtLeastOneUppercaseLetter_thenThrowError() {

        InvalidPasswordException exception = assertThrows(InvalidPasswordException.class, () -> this.userService.isValidPassword("b"));

        Optional<Error> error = exception.getErrors().stream().filter(e -> e.getType().equals("password-validation-005")).findFirst();

        assertTrue(error.isPresent());

        assertEquals("Campo password deve conter ao menos uma letra maiúscula.", error.get().getMessage());

    }

    @Test
    void whenPasswordHasNotAtLeastOneSpecialCharacter_thenThrowError() {

        InvalidPasswordException exception = assertThrows(InvalidPasswordException.class, () -> this.userService.isValidPassword("c"));

        Optional<Error> error = exception.getErrors().stream().filter(e -> e.getType().equals("password-validation-006")).findFirst();

        assertTrue(error.isPresent());

        assertEquals("Campo password deve conter ao menos um caractere especial.", error.get().getMessage());

    }

    @Test
    void whenPasswordHasNotAllowedSpecialCharacter_thenThrowError() {

        InvalidPasswordException exception = assertThrows(InvalidPasswordException.class, () -> this.userService.isValidPassword("."));

        Optional<Error> error = exception.getErrors().stream().filter(e -> e.getType().equals("password-validation-007")).findFirst();

        assertTrue(error.isPresent());

        assertEquals("Campo password deve conter apenas caraceteres válidos como: letras, digitos ou os caracteres especiais !@#$%^&*()-+", error.get().getMessage());

    }

    @Test
    void whenPasswordHasRepeatedCharacters_thenThrowError() {

        InvalidPasswordException exception = assertThrows(InvalidPasswordException.class, () -> this.userService.isValidPassword("AbTp9!foA"));

        Optional<Error> error = exception.getErrors().stream().filter(e -> e.getType().equals("password-validation-008")).findFirst();

        assertTrue(error.isPresent());

        assertEquals("Campo password não deve conter caracteres repetidos.", error.get().getMessage());

    }

}
