package com.primecheckerapp.validation;

public class LengthValidation implements Validation {
    private final int maxLength;

    public LengthValidation(int maxLength) {
        this.maxLength = maxLength;
    }

    public boolean validate(String input) {
        return input.length() <= maxLength;
    }

    public String getErrorMessage() {
        return "Sequence too long. Please enter a sequence with a maximum length of " + maxLength + ".";
    }
}
