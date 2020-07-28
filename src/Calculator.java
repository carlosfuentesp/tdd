public class Calculator {
    public int Add(String numbers) {
        int sum = 0;
        if( !numbers.isEmpty() ) {
            // String [] numbersSplit = numbers.split("//(.*?)\n");
            // TODO: make this work
            for (String splitNumber : numbers.split("[\\n,]+"))
                sum = sum + Integer.parseInt(splitNumber);
            return sum;
        }

        return 0;

    }
}
