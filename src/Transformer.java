import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Transformer {

    public List<String> transformStringToList(String rawNumbers){
        String regexDelimiter = findDelimiterByRegex(rawNumbers);
        String numbers = findNumbersForDefinedDelimiter(rawNumbers);
        List<String> numberList = Arrays.asList(numbers.split(regexDelimiter));
        return numberList;
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

    public void checkNegativeValues(Supplier<IntStream> negativeStream) {
        if (negativeStream.get().findFirst().isPresent()) {
            StringBuilder stringBuilder = new StringBuilder();
            negativeStream.get().forEach(stringBuilder::append);
            throw new NumberFormatException(String.format("negatives not allowed: %s", stringBuilder));
        }
    }
}
