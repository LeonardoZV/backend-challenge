package br.com.itau.backendchallenge.services;

import br.com.itau.backendchallenge.exceptions.InvalidPasswordException;
import br.com.itau.backendchallenge.models.Error;
import br.com.itau.backendchallenge.rules.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    public boolean isValidPassword(String password) throws InvalidPasswordException {

        List<Error> errorList = new LinkedList<>();

        if (!(new MustBeFilledRule()).isSatisfied(password)) {
            errorList.add(new Error("password-validation-001", "Campo password deve ser preenchido."));
            throw new InvalidPasswordException(errorList);
        }

        if (!(new MustContainNineOrMoreCharactersRule()).isSatisfied(password)) {
            errorList.add(new Error("password-validation-002", "Campo password deve conter nove ou mais caracteres."));
        }

        if (!(new MustContainAtLeastOneDigitRule()).isSatisfied(password)) {
            errorList.add(new Error("password-validation-003", "Campo password deve conter ao menos um dígito."));
        }

        if (!(new MustContainAtLeastOneLowercaseCharacterRule()).isSatisfied(password)) {
            errorList.add(new Error("password-validation-004", "Campo password deve conter ao menos uma letra minúscula."));
        }

        if (!(new MustContainAtLeastOneUppercaseCharacterRule()).isSatisfied(password)) {
            errorList.add(new Error("password-validation-005", "Campo password deve conter ao menos uma letra maiúscula."));
        }

        if (!(new MustContainAtLeastOneSpecialCharacterRule()).isSatisfied(password)) {
            errorList.add(new Error("password-validation-006", "Campo password deve conter ao menos um caractere especial."));
        }

        if (!(new MustContainOnlyAllowedCharactersRule()).isSatisfied(password)) {
            errorList.add(new Error("password-validation-007", "Campo password deve conter apenas caraceteres válidos como: letras, digitos ou os caracteres especiais !@#$%^&*()-+"));
        }

        if (!(new MustNotContainRepeatedCharactersRule()).isSatisfied(password)) {
            errorList.add(new Error("password-validation-008", "Campo password não deve conter caracteres repetidos."));
        }

        if (!errorList.isEmpty())
            throw new InvalidPasswordException(errorList);

        return true;

    }

}
