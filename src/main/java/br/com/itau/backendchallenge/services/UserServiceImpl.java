package br.com.itau.backendchallenge.services;

import br.com.itau.backendchallenge.exceptions.InvalidPasswordException;
import br.com.itau.backendchallenge.models.Error;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    public boolean isValidPassword(String password) throws InvalidPasswordException {

        List<Error> errorList = new ArrayList<>();

        if (password == null || password.isEmpty()) {
            errorList.add(new Error(400, "password-validation-001", "Campo password não preenchido."));
            throw new InvalidPasswordException(errorList);
        }

        if (!containsNineOrMoreCharacters(password)) {
            errorList.add(new Error(400, "password-validation-002", "Campo password deve conter nove ou mais caracteres."));
        }

        if (!containsAtLeastOneDigit(password)) {
            errorList.add(new Error(400, "password-validation-003", "Campo password deve conter ao menos um dígito."));
        }

        if (!containsAtLeastOneLowercaseCharacter(password)) {
            errorList.add(new Error(400, "password-validation-004", "Campo password deve conter ao menos uma letra minúscula."));
        }

        if (!containsAtLeastOneUppercaseCharacter(password)) {
            errorList.add(new Error(400, "password-validation-005", "Campo password deve conter ao menos uma letra maiúscula."));
        }

        if (!containsAtLeastOneSpecialCharacter(password)) {
            errorList.add(new Error(400, "password-validation-006", "Campo password deve conter ao menos um caractere especial."));
        }

        if (!containsOnlyAllowedCharacters(password)) {
            errorList.add(new Error(400, "password-validation-007", "Campo password deve conter apenas caraceteres válidos como: letras, digitos ou os caracteres especiais !@#$%^&*()-+"));
        }

        if (containsRepeatedCharacters(password)) {
            errorList.add(new Error(400, "password-validation-008", "Campo password não deve conter caracteres repetidos."));
        }

        if (errorList.size() > 0)
            throw new InvalidPasswordException(errorList);

        return true;

    }

    private boolean containsNineOrMoreCharacters(String password) {
        Pattern pattern = Pattern.compile(".{9,}");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private boolean containsAtLeastOneDigit(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[0-9]).+$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private boolean containsAtLeastOneLowercaseCharacter(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[a-z]).+$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private boolean containsAtLeastOneUppercaseCharacter(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[A-Z]).+$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private boolean containsAtLeastOneSpecialCharacter(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[!@#$%^&*()\\-+]).+$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private boolean containsOnlyAllowedCharacters(String password) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9!@#$%^&*()\\-+]*$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private boolean containsRepeatedCharacters(String password) {
        Pattern pattern = Pattern.compile("^(?=.*(.).*\\1).*+$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
