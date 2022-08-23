package br.com.itau.backendchallenge.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MustContainNineOrMoreCharactersRule implements IRule {

    public boolean isSatisfied(String password) {
        Pattern pattern = Pattern.compile(".{9,}");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
