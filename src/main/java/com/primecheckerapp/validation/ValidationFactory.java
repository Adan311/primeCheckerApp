package com.primecheckerapp.validation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// This class provides a way to create different types of validation objects.
public class ValidationFactory {
    private static final Logger logger = LogManager.getLogger(ValidationFactory.class);

    public static Validation getValidation(String type, int maxLength) {
        logger.debug("Creating validation object of type: " + type);
        // Returns an instance of the requested validation type.
        switch (type) {
            case "NonEmpty":
                return new NonEmptyValidation(); // Validation to check for non-empty input.
            case "Numeric":
                return new NumericValidation(); // Validation to check for numeric input.
            case "Length":
                return new LengthValidation(maxLength); // Validation to check input length.
            default:
                logger.error("Unknown validation type: " + type);
                throw new IllegalArgumentException("Unknown validation type"); // Error for unknown type.
        }
    }
}