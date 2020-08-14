import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    private String errorMessage = "";

    public int Add(String numbers) throws NegativeNumberException {
        int sum = 0;
        int transformedNumber = 0;
        if (!numbers.isEmpty()) {
            int index = 0;
            String [] splitArrayOfNumbers = numbers.split(findDelimiterByRegex(numbers));
            for (String splitNumber : splitArrayOfNumbers)
                if (!splitNumber.equals("//")) {
                    transformedNumber = transformNumberForSum(splitNumber);
                    if ( transformedNumber < 0 ) {
                        addToErrorMessageForExceptionThrow(splitNumber);
                    }
                    if ( index == splitArrayOfNumbers.length - 2 && !errorMessage.equals("") ) {
                        throw new NegativeNumberException("negatives not allowed - " + errorMessage);
                    }
                    sum = sum + transformedNumber;
                    index++;
                }
            return sum;
        }

        return 0;
    }

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

    private void addToErrorMessageForExceptionThrow(String number) {
        errorMessage = errorMessage + number + " ";
    }

    public static class NegativeNumberException extends Exception {
        public NegativeNumberException(String errorMessage) {
            super(errorMessage);
        }
    }

}
