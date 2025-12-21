import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class LambdaExpressions {
    public static void main(String[] args) {
        // Traditional anonymous class
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Traditional runnable");
            }
        };
        
        // Lambda expression
        Runnable r2 = () -> System.out.println("Lambda runnable");
        
        r1.run();
        r2.run();
        
        // Lambda with parameters
        Comparator<Integer> comparator = (a, b) -> a.compareTo(b);
        System.out.println("Compare 5 and 3: " + comparator.compare(5, 3));
        
        // Lambda with multiple statements
        Calculator add = (a, b) -> {
            System.out.println("Adding " + a + " and " + b);
            return a + b;
        };
        System.out.println("Result: " + add.calculate(10, 20));
        
        // Lambda with collections
        List<String> names = Arrays.asList("John", "Jane", "Bob", "Alice");
        
        // forEach with lambda
        names.forEach(name -> System.out.println("Hello, " + name));
        
        // Sort with lambda
        names.sort((s1, s2) -> s1.compareTo(s2));
        System.out.println("Sorted: " + names);
    }
}

@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}
