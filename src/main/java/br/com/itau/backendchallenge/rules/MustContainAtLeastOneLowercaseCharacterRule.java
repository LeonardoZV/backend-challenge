package br.com.itau.backendchallenge.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MustContainAtLeastOneLowercaseCharacterRule implements IRule {

    public boolean isSatisfied(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[a-z]).+$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}