import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private final Calculator calculator = new Calculator();

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

        int actualResult = calculator.add(numbers);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldReturnSumOfAnyNumbers() {
        int expectedResult = 6;
        String numbers = "1,2,3";

        int actualResult = calculator.add(numbers);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldReturnSumOfNumbersWithNewLinesAndCommas() {
        int expectedResult = 6;
        String numbers = "1\n2,3";

        int actualResult = calculator.add(numbers);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldReturnSumOfNumbersWithDelimiterAndSemicolon() {
        int expectedResult = 3;
        String numbers = "//;\n1;2";

        int actualResult = calculator.add(numbers);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldThrownAnNumberFormatExceptionWhenANegativeNumberIsProvided() {
        String numbers = "//;\n1;-2;-4";

        Exception exception = assertThrows(NumberFormatException.class, () -> calculator.add(numbers));

        String expectedMessage = "negatives not allowed: -2-4";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldFindTheListOfNumbersForDefinedLimiter() {
        String expectedResult = "1|3|4|5";
        String numbers = "//|\n1|3|4|5";

        String actualResult = calculator.findNumbersForDefinedDelimiter(numbers);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldSumAllNumbersFromAGivenList() {
        int expectedResult = 12;
        List<String> numbers = Arrays.asList("2", "3", "1", "6");
        int actualResult = calculator.sum(numbers);

        assertEquals(expectedResult, actualResult);
    }
}
