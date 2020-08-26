import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Calculator {
    private Transformer transformer;

    public Calculator(Transformer transformer) {
        this.transformer = transformer;
    }

    public int add(String input) {
        List<String> responseList = transformer.transformStringToList(input);
        return responseList.get(0).equals("") ? 0 : sum(responseList);
    }

    public int sum(List<String> numberList) {
        Supplier<IntStream> intStream = () -> new ArrayList<>(numberList).stream().mapToInt(Integer::parseInt);
        Supplier<IntStream> negativeStream = () -> intStream.get().filter(num -> num < 0);
        transformer.checkNegativeValues(negativeStream);
        return intStream.get().sum();
    }
}
