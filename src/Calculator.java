import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    public String findDelimiterByRegex(String numbers) {
        Pattern pattern = Pattern.compile("(?<=//).*?(?=\n)");
        Matcher matcher = pattern.matcher(numbers);
        String delimiter = "[\\n,]+";

        if (matcher.find()) delimiter = matcher.group(0);

        return delimiter;
    }

    private int transformNumberForSum(String number) {
        return Integer.parseInt(number.replace("\n", ""));
    }

    public int Add(String numbers) {
        int sum = 0;
        if( !numbers.isEmpty() ) {
            for (String splitNumber : numbers.split(findDelimiterByRegex(numbers)))
                if (!splitNumber.equals("//")) sum = sum + transformNumberForSum(splitNumber);
            return sum;
        }

        return 0;

    }
}
