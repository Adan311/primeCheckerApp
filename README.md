Prime Checker App
Prime Checker App is a simple Java command-line tool that allows users to enter a sequence of numbers, identifies any prime numbers within the sequence, and saves them for future use. It also validates the input to ensure it's numeric and meets the specified length requirements.

How It Works
Input: The user enters a sequence of numbers.
Validation: The app checks that the input is:
Not empty.
Numeric.
Within the allowed length (maximum 100 characters).
Prime Checking: The app finds all prime numbers within the sequence.
Saving: Prime numbers are saved to a file (primes.txt) and cached for future runs.
Example Usage
When you run the app, you'll be prompted to enter a sequence of numbers.
The app will check if there are any prime numbers in the sequence and display them.
Example 1: Prime Numbers Found
bash
Copy code
Enter a sequence to find prime numbers (or type 'exit' to exit the program): 2357
Prime numbers in the sequence 2357: [2, 3, 5, 7]
Example 2: No Prime Numbers Found
bash
Copy code
Enter a sequence to find prime numbers (or type 'exit' to exit the program): 468
No prime numbers found in the sequence.
Example 3: Invalid Input (Non-Numeric)
bash
Copy code
Enter a sequence to find prime numbers (or type 'exit' to exit the program): abc123
Validation failed: Input contains non-numeric characters.
Exiting the App
To exit, simply type exit when prompted.

