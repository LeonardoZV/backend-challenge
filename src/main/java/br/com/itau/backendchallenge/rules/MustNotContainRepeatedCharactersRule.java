package br.com.itau.backendchallenge.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MustNotContainRepeatedCharactersRule implements IRule {

    public boolean isSatisfied(String password) {
        Pattern pattern = Pattern.compile("^(?=.*(.).*\\1).*+$");
        Matcher matcher = pattern.matcher(password);
        return !matcher.matches();
    }

}
