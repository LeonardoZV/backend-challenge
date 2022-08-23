package br.com.itau.backendchallenge.rules;

public class MustBeFilledRule implements IRule {

    public boolean isSatisfied(String password) {
        return !(password == null || password.isEmpty());
    }

}
