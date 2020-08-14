import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    public void shouldReturnZeroWhenEmptyStringIsProvided() throws Calculator.NegativeNumberException {
        int expectedResult = 0;
        String numbers = "";

        int actualResult = calculator.Add(numbers);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldReturnSumOfAnyNumbers() throws Calculator.NegativeNumberException {
        int expectedResult = 6;
        String numbers = "1,2,3";

        int actualResult = calculator.Add(numbers);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldReturnSumOfNumbersWithNewLinesAndCommas() throws Calculator.NegativeNumberException {
        int expectedResult = 6;
        String numbers = "1\n2,3";

        int actualResult = calculator.Add(numbers);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldReturnSumOfNumbersWithDelimiterAndSemicolon() throws Calculator.NegativeNumberException {
        int expectedResult = 3;
        String numbers = "//;\n1;2";

        int actualResult = calculator.Add(numbers);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldReturnRegexWithAPatternIsFound() {
        String pattern = "//%s\n";
        String values = "1%s2";
        String numbers = pattern + values;

        String expectedResult = ";";
        String actualResult = calculator.findDelimiterByRegex(String.format(numbers, expectedResult, expectedResult));

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldReturnCharRegexWhenThereIsNoPattern(){
        String numbers = "1/n2,3";

        String expectedResult = "[\\n,]+";
        String actualResult = calculator.findDelimiterByRegex(numbers);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldReturnExceptionForSingleNegativeNumber() throws Calculator.NegativeNumberException {
        String expectedResult = "negatives not allowed - -2 ";
        String numbers = "//;\n1;-2";

        Exception exception = assertThrows(Calculator.NegativeNumberException.class, () -> {
            calculator.Add(numbers);
        });

        String actualResult = exception.getMessage();

        assertTrue(actualResult.contains(expectedResult));

    }

    @Test
    public void shouldReturnExceptionForMultipleNegativeNumbers() throws Calculator.NegativeNumberException {
        String expectedResult = "negatives not allowed - -2 -3 -5";
        String numbers = "//;\n1;-2;-3;4;-5;6";

        Exception exception = assertThrows(Calculator.NegativeNumberException.class, () -> {
            calculator.Add(numbers);
        });

        String actualResult = exception.getMessage();

        assertTrue(actualResult.contains(expectedResult));

    }
}
