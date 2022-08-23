package br.com.itau.backendchallenge.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MustContainAtLeastOneDigitRule implements IRule {

    public boolean isSatisfied(String password) {
        Pattern pattern = Pattern.compile("^(?=.*\\d).+$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
