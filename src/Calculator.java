import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Calculator {

    public int add(String rawNumbers) {
        String regexDelimiter = findDelimiterByRegex(rawNumbers);
        String numbers = findNumbersForDefinedDelimiter(rawNumbers);
        List<String> numberList = Arrays.asList(numbers.split(regexDelimiter));
        return numberList.get(0).equals("") ? 0 : sum(numberList);
    }

    public String findDelimiterByRegex(String numbers) {
        Pattern pattern = Pattern.compile("(?<=//).*?(?=\n)");
        Matcher matcher = pattern.matcher(numbers);
        String regexDelimiter = "[\\n,]+";

        if (matcher.find()) regexDelimiter = matcher.group(0);

        return regexDelimiter;
    }

    public String findNumbersForDefinedDelimiter(String numbers) {
        int beginIndexForDefinedLimiter = 4;
        String prefixForDefinedDelimiter = "//";
        if (numbers.startsWith(prefixForDefinedDelimiter)) {
            numbers = numbers.substring(beginIndexForDefinedLimiter);
        }
        return numbers;
    }

    public int sum(List<String> numberList) {
        Supplier<IntStream> intStream = () -> new ArrayList<>(numberList).stream().mapToInt(Integer::parseInt);
        Supplier<IntStream> negativeStream = () -> intStream.get().filter(num -> num < 0);
        checkNegativeValues(negativeStream);
        return intStream.get().sum();
    }

    private void checkNegativeValues(Supplier<IntStream> negativeStream) {
        if (negativeStream.get().findFirst().isPresent()) {
            StringBuilder stringBuilder = new StringBuilder();
            negativeStream.get().forEach(stringBuilder::append);
            throw new NumberFormatException(String.format("negatives not allowed: %s", stringBuilder));
        }
    }
}