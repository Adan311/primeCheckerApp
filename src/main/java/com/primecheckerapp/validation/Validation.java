package com.primecheckerapp.validation;

// This interface defines methods for input validation.
public interface Validation {
    boolean validate(String input); // Method to validate the input.

    String getErrorMessage(); // Method to get the error message if validation fails.
}
