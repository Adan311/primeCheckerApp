import com.primecheckerapp.validation.LengthValidation;
import com.primecheckerapp.validation.NonEmptyValidation;
import com.primecheckerapp.validation.NumericValidation;
import org.junit.Test;
import static org.junit.Assert.*;

public class ValidationTest {

    @Test
    public void testNonEmptyValidation() {
        NonEmptyValidation nonEmptyValidation = new NonEmptyValidation();
        assertTrue(nonEmptyValidation.validate("123"));
        assertFalse(nonEmptyValidation.validate(""));
    }

    @Test
    public void testNumericValidation() {
        NumericValidation numericValidation = new NumericValidation();
        assertTrue(numericValidation.validate("123"));
        assertFalse(numericValidation.validate("abc"));
        assertTrue(numericValidation.validate("abc123"));
    }

    @Test
    public void testLengthValidation() {
        LengthValidation lengthValidation = new LengthValidation(5);
        assertTrue(lengthValidation.validate("123"));
        assertTrue(lengthValidation.validate("12345"));
        assertFalse(lengthValidation.validate("123456"));
    }
}