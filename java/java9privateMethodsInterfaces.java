public interface Calculator {
    // Public default method
    default int add(int a, int b) {
        return compute(a, b, '+');
    }
    
    default int subtract(int a, int b) {
        return compute(a, b, '-');
    }
    
    // Private method (Java 9+)
    private int compute(int a, int b, char operation) {
        System.out.println("Computing: " + a + " " + operation + " " + b);
        switch (operation) {
            case '+': return a + b;
            case '-': return a - b;
            default: throw new IllegalArgumentException("Unknown operation");
        }
    }
    
    // Private static method
    private static void log(String message) {
        System.out.println("LOG: " + message);
    }
}

class CalculatorImpl implements Calculator {
    public static void main(String[] args) {
        Calculator calc = new CalculatorImpl();
        System.out.println("Add: " + calc.add(10, 5));
        System.out.println("Subtract: " + calc.subtract(10, 5));
    }
}
