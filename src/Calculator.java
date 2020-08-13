import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Calculator {

    public int add(String numbers) {
        String regexDelimiter = findDelimiterByRegex(numbers);
        if (numbers.startsWith("//")) {
            numbers = numbers.substring(4);
        }
        List<String> numberList = Arrays.asList(numbers.split(regexDelimiter));
        return numberList.get(0).equals("") ? 0 : sum(numberList);
    }

    private int sum(List<String> numberList) {
        Supplier<Stream<String>> stream = () -> new ArrayList<>(numberList).stream();
        Supplier<IntStream> intStream = () -> stream.get().mapToInt(Integer::parseInt);
        Supplier<IntStream> negativeStream = () -> intStream.get().filter(num -> num < 0);
        checkNegativeValues(negativeStream);
        return intStream.get().sum();
    }

    private void checkNegativeValues(Supplier<IntStream> negativeStream) {
        if (negativeStream.get().count() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            negativeStream.get().forEach(stringBuilder::append);
            throw new NumberFormatException("negatives not allowed: " + stringBuilder);
        }
    }

    public String findDelimiterByRegex(String numbers) {
        Pattern pattern = Pattern.compile("(?<=//).*?(?=\n)");
        Matcher matcher = pattern.matcher(numbers);
        String regexDelimiter = "[\\n,]+";

        if (matcher.find()) regexDelimiter = matcher.group(0);

        return regexDelimiter;
    }

}