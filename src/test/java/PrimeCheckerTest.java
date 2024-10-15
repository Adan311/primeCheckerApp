import com.primecheckerapp.PrimeCheckerApp;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class PrimeCheckerTest {

    @Test
    void testIsPrime() {
        assertTrue(PrimeCheckerApp.isPrime(BigInteger.valueOf(2)));
        assertTrue(PrimeCheckerApp.isPrime(BigInteger.valueOf(3)));
        assertFalse(PrimeCheckerApp.isPrime(BigInteger.valueOf(4)));
        assertTrue(PrimeCheckerApp.isPrime(BigInteger.valueOf(5)));
        assertFalse(PrimeCheckerApp.isPrime(BigInteger.valueOf(1)));
    }

    @Test
    public void testFindPrimeNumbers() {
        // Assuming you have a valid "sequence" string somewhere
        String sequence = "2, 3, 4, 5, 1";
        Set<BigInteger> primes = PrimeCheckerApp.findPrimeNumbers(sequence); // Pass "sequence" as argument
        assertTrue(primes.contains(BigInteger.valueOf(2)));
        assertTrue(primes.contains(BigInteger.valueOf(3)));
        assertFalse(primes.contains(BigInteger.valueOf(4)));
        assertTrue(primes.contains(BigInteger.valueOf(5)));
        assertFalse(primes.contains(BigInteger.valueOf(1)));
    }

}