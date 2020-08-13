import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    public String findDelimiterByRegex(String numbers) {
        Pattern pattern = Pattern.compile("(?<=//).*?(?=\n)");
        Matcher matcher = pattern.matcher(numbers);
        String regexDelimiter = "[\\n,]+";

        if (matcher.find()) regexDelimiter = matcher.group(0);

        return regexDelimiter;
    }

    public int Add(String numbers) {
        String regexDelimiter = findDelimiterByRegex(numbers);
        if (numbers.startsWith("//")) {
            numbers = numbers.substring(4);
        }
        List<String> numberList = Arrays.asList(numbers.split(regexDelimiter));
        return numberList.get(0).equals("") ? 0 : Sum(numberList);
    }

    private int Sum(List<String> numberList) {
        return new ArrayList<>(numberList).stream().mapToInt(this::parseToInt).sum();
    }

    private int parseToInt(String num) {
        int number = Integer.parseInt(num);
        if (number < 0) {
            throw new NumberFormatException("negatives not allowed:" + number);
        }
        return number;
    }
}