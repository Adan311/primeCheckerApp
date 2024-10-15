package com.primecheckerapp.validation;

public class NumericValidation implements Validation {
    public boolean validate(String input) {
        return input.matches(".*\\d.*");
    }

    public String getErrorMessage() {
        return "Sequence must contain at least one numeric character.";
    }
}
