import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator {
    public int Add(String numbers) {
        List<String> numberList = Arrays.asList(numbers.split("[\\n,]+"));
        return numberList.get(0).equals("") ? 0 : new ArrayList<>(numberList).stream().mapToInt(Integer::parseInt).sum();
    }
}