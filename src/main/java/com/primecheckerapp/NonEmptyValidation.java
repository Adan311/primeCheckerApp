package com.primecheckerapp;

// Step 2: Implement concrete validation classes
public class NonEmptyValidation implements Validation {
    public boolean validate(String input) {
        return !input.isEmpty();
    }

    public String getErrorMessage() {
        return "Please enter a non-empty sequence.";
    }
}
