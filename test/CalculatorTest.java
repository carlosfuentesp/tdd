import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    public void whenExceptionThrown_thenAssertionSucceeds() {

        String numbers = "1,2,-3";

        Exception exception = assertThrows(NumberFormatException.class, () -> calculator.Add(numbers));

        String expectedMessage = "negatives not allowed";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldReturnCharRegexWhenThereIsNoPattern() {
        String numbers = "1/n2,3";

        String expectedResult = "[\\n,]+";
        String actualResult = calculator.findDelimiterByRegex(numbers);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldReturnZeroWhenEmptyStringIsProvided() {
        int expectedResult = 0;
        String numbers = "";

        int actualResult = calculator.Add(numbers);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldReturnSumOfAnyNumbers() {
        int expectedResult = 6;
        String numbers = "1,2,3";

        int actualResult = calculator.Add(numbers);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldReturnSumOfNumbersWithNewLinesAndCommas() {
        int expectedResult = 6;
        String numbers = "1\n2,3";

        int actualResult = calculator.Add(numbers);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldReturnSumOfNumbersWithDelimiterAndSemicolon() {
        int expectedResult = 3;
        String numbers = "//;\n1;2";

        int actualResult = calculator.Add(numbers);

        assertEquals(expectedResult, actualResult);
    }


    @Test
    public void shouldThrownAnNumberFormatExceptionWhenANegativeNumberIsProvided() {
        String numbers = "//;\n1;-2;-4";

        Exception exception = assertThrows(NumberFormatException.class, () -> calculator.Add(numbers));

        String expectedMessage = "negatives not allowed";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }



}
