import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransformerTest {

    private final Transformer transformer = new Transformer();

    @Test
    public void shouldReturnRegexWithAPatternIsFound() {
        String pattern = "//%s\n";
        String values = "1%s2";
        String numbers = pattern + values;

        String expectedResult = ";";
        String actualResult = transformer.findDelimiterByRegex(String.format(numbers, expectedResult, expectedResult));

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldReturnCharRegexWhenThereIsNoPattern() {
        String numbers = "1/n2,3";

        String expectedResult = "[\\n,]+";
        String actualResult = transformer.findDelimiterByRegex(numbers);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldFindTheListOfNumbersForDefinedLimiter() {
        String expectedResult = "1|3|4|5";
        String numbers = "//|\n1|3|4|5";

        String actualResult = transformer.findNumbersForDefinedDelimiter(numbers);

        assertEquals(expectedResult, actualResult);
    }
}
