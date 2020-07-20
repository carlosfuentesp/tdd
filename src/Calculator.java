public class Calculator {
    public int Add(String numbers) {
        if (numbers.isEmpty()) return 0;
        String[] splitNumbers = numbers.split(",");
        int sum = 0;
        for (String splitNumber : splitNumbers) {
            sum = sum + Integer.parseInt(splitNumber);
        }
        return sum;
    }
}
