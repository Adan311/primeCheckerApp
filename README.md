# Prime Checker App Usage

## Overview
Prime Checker App is a command-line application that allows users to input a sequence of numbers and find all the prime numbers within that sequence. The prime numbers, along with the username, will be saved in a file called `primes.txt`.

## How to Run the App
1. Start the application.
2. Enter your username when prompted.
3. Enter a sequence of numbers. The app will find all the prime numbers in the sequence and display them.
4. The results (prime numbers and username) are saved to `primes.txt`.
5. To exit the program, type `exit` and confirm.

## Example Output
Welcome to the Prime Checker App! Please enter your username: adan Enter a sequence to find prime numbers (or type 'exit' to exit the program): 12345 Prime numbers in the sequence 12345 for user adan: [2, 3, 5, 23]

Prime numbers `[2, 3, 5, 23]` will be saved in `primes.txt` for user `adan`.

### Error Handling

#### Empty Sequence

Enter a sequence to find prime numbers (or type 'exit' to exit the program): 
22:24:43.438 [main] ERROR com.primecheckerapp.PrimeCheckerApp 
Non-empty validation failed for sequence: Please enter a non-empty sequence.


#### Invalid Characters

Enter a sequence to find prime numbers (or type 'exit' to exit the program): 
22:24:45.391 [main] ERROR com.primecheckerapp.PrimeCheckerApp 
Numeric validation failed for sequence: . Sequence must contain at least one numeric character.

#### Sequence with Non-Numeric Characters

Enter a sequence to find prime numbers (or type 'exit' to exit the program):1234567f 
Warning: Sequence contains non-numeric characters 'f'. Ignoring these characters. 
Prime numbers in the sequence 1234567f for user adan: [2, 3, 67, 5, 7, 23, 4567]

### Exiting the Program
Enter a sequence to find prime numbers (or type 'exit' to exit the program): exit Are you sure you want to exit the program? (yes/no) yes

## File Output
The prime numbers and username are saved in `primes.txt`. For example:

adan: 2
adan: 3
adan: 5
adan: 7

## Conclusion
The Prime Checker App is a simple tool for finding prime numbers within a numeric sequence. Follow the steps above to use it, and ensure that the sequence is valid for proper results.
