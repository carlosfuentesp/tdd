import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    private final Calculator calculator = new Calculator();

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
}
