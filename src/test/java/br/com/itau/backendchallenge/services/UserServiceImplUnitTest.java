package br.com.itau.backendchallenge.services;

import br.com.itau.backendchallenge.exceptions.InvalidPasswordException;
import br.com.itau.backendchallenge.models.Error;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            ";password-validation-001;Campo password deve ser preenchido.",
            "'';password-validation-001;Campo password deve ser preenchido.",
            "a;password-validation-002;Campo password deve conter nove ou mais caracteres.",
            "abcdefghi;password-validation-003;Campo password deve conter ao menos um dígito.",
            "A;password-validation-004;Campo password deve conter ao menos uma letra minúscula.",
            "b;password-validation-005;Campo password deve conter ao menos uma letra maiúscula.",
            "c;password-validation-006;Campo password deve conter ao menos um caractere especial.",
            ".;password-validation-007;Campo password deve conter apenas caraceteres válidos como: letras, digitos ou os caracteres especiais !@#$%^&*()-+",
            "AbTp9!foA;password-validation-008;Campo password não deve conter caracteres repetidos."
    })
    void whenPasswordIsNotValid_thenThrowAndLogError(String password, String errorType, String errorMessage) {

        InvalidPasswordException exception = assertThrows(InvalidPasswordException.class, () -> this.userService.isValidPassword(password));

        Optional<Error> error = exception.getErrors().stream().filter(e -> e.getType().equals(errorType)).findFirst();

        assertTrue(error.isPresent());

        assertEquals(errorMessage, error.get().getMessage());

    }

}
