package com.primecheckerapp;

import com.primecheckerapp.validation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;
import java.math.BigInteger;


public class PrimeCheckerApp {
    private static final Logger logger = LogManager.getLogger(PrimeCheckerApp.class);

    private static final Set<Integer> primeCache = new HashSet<>();// Cache to store prime numbers for quick access.

    private static final String DEFAULT_FILE_PATH = "src/resources/primes.txt";// Default file path where prime numbers are stored.

    private static final int MAX_SEQUENCE_LENGTH = 100; // Maximum length of the numeric sequence to be processed.

    private static String username; // Variable to store the username input by the user.


    public static void main(String[] args) {
        logger.info("Starting Prime Checker App");
        Scanner scanner = new Scanner(System.in);// Create a scanner to read user input.

        System.out.println("Welcome to the Prime Checker App!");

        readPrimesFromFile(DEFAULT_FILE_PATH);// Read prime numbers from file and populate the cache.

        System.out.print("Please enter your username: ");
        username = scanner.nextLine().trim();

        readPrimesFromFile(DEFAULT_FILE_PATH);// Read prime numbers from file and populate the cache again.

        // Instantiate validations using the factory.
        Validation nonEmptyValidation = ValidationFactory.getValidation("NonEmpty", MAX_SEQUENCE_LENGTH);
        Validation numericValidation = ValidationFactory.getValidation("Numeric", MAX_SEQUENCE_LENGTH);
        Validation lengthValidation = ValidationFactory.getValidation("Length", MAX_SEQUENCE_LENGTH);

        while (true) {// Main loop of the application.

            System.out.print("Enter a sequence to find prime numbers (or type 'exit' to exit the program): ");
            String sequence = scanner.nextLine().trim();

            if (sequence.equalsIgnoreCase("exit")) {// Check if the user wants to exit the program.

                System.out.println("Are you sure you want to exit the program? (yes/no)");
                String response = scanner.nextLine().trim();
                if (response.equalsIgnoreCase("yes")) {
                    logger.info("Exiting Prime Checker App");
                    break;
                } else {
                    continue; // Go back to the beginning of the loop.
                }
            }

            if (!nonEmptyValidation.validate(sequence)) {  // Apply validations.
                logger.error("Non-empty validation failed for sequence: " + sequence);
                System.out.println(nonEmptyValidation.getErrorMessage());
                continue;
            }
            if (!numericValidation.validate(sequence)) {
                logger.error("Numeric validation failed for sequence: " + sequence);
                System.out.println(numericValidation.getErrorMessage());
                continue;
            }
            if (!lengthValidation.validate(sequence)) {
                logger.error("Length validation failed for sequence: " + sequence);
                System.out.println(lengthValidation.getErrorMessage());
                continue;
            }

            Set<BigInteger> primeNumbers = findPrimeNumbers(sequence); // Find prime numbers in the sequence.

            if (!primeNumbers.isEmpty() && !primeNumbers.contains(BigInteger.ZERO)) { // Check if prime numbers were found.

                logger.info("Found prime numbers in sequence: " + sequence);
                System.out.println("Prime numbers in the sequence " + sequence + " for user " + username + ": " + primeNumbers);

                writePrimesToFile(DEFAULT_FILE_PATH, username, primeNumbers); // Write prime numbers to file.
            }
        }
        scanner.close(); // Close the scanner.
    }

    public static void readPrimesFromFile(String filePath) {
        logger.info("Reading primes from file: " + filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line; // Read each line from the file
            while ((line = reader.readLine()) != null) {
                line = line.trim(); // Remove leading/trailing whitespace
                if (line.isEmpty()) { // Skip empty lines
                    continue;
                }
                String[] parts = line.split(":"); // Split line into username and prime number
                if (parts.length != 2) { // Invalid line format
                    logger.error("Skipping invalid line in file: " + line);
                    System.err.println("Skipping invalid line in file: " + line);
                    continue;
                }
                try {
                    int prime = Integer.parseInt(parts[1].trim()); // Parse prime number
                    primeCache.add(prime); // Add to cache
                } catch (NumberFormatException e) { // Invalid number format
                    logger.error("Skipping invalid number in file: " + line);
                    System.err.println("Skipping invalid number in file: " + line);
                }
            }
        } catch (IOException e) { // File I/O error
            logger.error("Error reading from file: " + e.getMessage());
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    public static void writePrimesToFile(String filePath, String username, Set<BigInteger> primeNumbers) {
        logger.info("Writing prime numbers to file: " + filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            Set<Integer> existingPrimes = new HashSet<>(); // Store existing primes
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":"); // Split line into username and prime number
                if (parts.length != 2) { // Invalid line format
                    logger.error("Skipping invalid line in file: " + line);
                    System.err.println("Skipping invalid line in file: " + line);
                    continue;
                }
                try {
                    int prime = Integer.parseInt(parts[1].trim()); // Parse prime number
                    existingPrimes.add(prime); // Add to existing primes
                } catch (NumberFormatException e) { // Invalid number format
                    logger.error("Skipping invalid number in file: " + line);
                    System.err.println("Skipping invalid number in file: " + line);
                }
            }

            Set<Integer> uniqueNewPrimes = new HashSet<>(); // Store new unique primes
            for (BigInteger prime : primeNumbers) {
                if (!existingPrimes.contains(prime.intValue())) { // Check if prime is new
                    uniqueNewPrimes.add(prime.intValue()); // Add to new primes
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                for (int prime : uniqueNewPrimes) {
                    logger.info("Writing prime number to file: " + prime);
                    writer.write(username + ": " + String.valueOf(prime)); // Write prime to file
                    writer.newLine();
                }
            } catch (IOException e) { // File I/O error
                logger.error("Error writing to file: " + e.getMessage());
                System.err.println("Error writing to file: " + e.getMessage());
                // Consider a recovery action, such as retrying the operation or notifying the user.
            }
        } catch (IOException e) { // File I/O error
            logger.error("Error reading from file: " + e.getMessage());
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    public static Set<BigInteger> findPrimeNumbers(String sequence) {
        logger.debug("Finding prime numbers in sequence: " + sequence);
        Set<BigInteger> primeNumbers = new HashSet<>(); // Store prime numbers
        final int MAX_COMBINATION_LENGTH = 10; // Set a reasonable limit.

        // Check for non-numeric characters in the sequence
        String nonNumericChars = sequence.replaceAll("[0-9]+", "");
        if (!nonNumericChars.isEmpty()) {
            logger.warn("Sequence contains non-numeric characters '" + nonNumericChars + "'. Ignoring these characters.");
            System.out.println("Warning: Sequence contains non-numeric characters '" + nonNumericChars + "'. Ignoring these characters.");
        }
        // Remove non-numeric characters from the sequence
        sequence = sequence.replaceAll("[^0-9]+", "");

        // Generate all possible combinations of the sequence
        for (int length = 1; length <= Math.min(sequence.length(), MAX_COMBINATION_LENGTH); length++) {
            for (int i = 0; i <= sequence.length() - length; i++) {
                String combination = sequence.substring(i, i + length);
                BigInteger number = new BigInteger(combination);

                // Skip numbers less than 1
                if (number.compareTo(BigInteger.ONE) < 0) {
                    continue;
                }
                // Check if number is prime
                if (primeCache.contains(number.intValue())) {
                    logger.debug("Number is prime and in cache: " + number);
                    primeNumbers.add(number); // Add to prime numbers if already in cache
                } else if (isPrime(number)) {
                    logger.debug("Number is prime: " + number);
                    primeNumbers.add(number); // Add to prime numbers if prime
                    if (!primeCache.contains(number.intValue())) {
                        logger.debug("Adding prime number to cache: " + number);
                        primeCache.add(number.intValue()); // Add to cache
                    }
                }
            }
        }
        // Print message if no prime numbers found
        if (primeNumbers.isEmpty()) {
            logger.info("No prime numbers found in the sequence.");
            System.out.println("No prime numbers found in the sequence.");
        }
        return primeNumbers;
    }

    public static boolean isPrime(BigInteger number) {
        logger.debug("Checking if number is prime: " + number);
        // Numbers less than 1 are not prime
        if (number.compareTo(BigInteger.ONE) < 0) {
            return false;
        }
        // 1 is not prime
        if (number.equals(BigInteger.ONE)) {
            return false;
        }
        // 2 is prime
        if (number.equals(BigInteger.valueOf(2))) {
            return true;
        }
        // Even numbers are not prime
        if (number.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            return false;
        }
        // Check for divisibility by odd numbers up to sqrt(n)
        for (BigInteger i = BigInteger.valueOf(3); i.multiply(i).compareTo(number) <= 0; i = i.add(BigInteger.valueOf(2))) {
            if (number.mod(i).equals(BigInteger.ZERO)) {
                return false;
            }
        }
        return true;
    }
}