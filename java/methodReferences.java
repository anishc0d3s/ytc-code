import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReferencesExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        
        // 1. Reference to static method
        // Lambda: n -> Integer.parseInt(n)
        Function<String, Integer> parser = Integer::parseInt;
        System.out.println("Parsed: " + parser.apply("123"));
        
        // 2. Reference to instance method of particular object
        String prefix = "Hello, ";
        // Lambda: s -> prefix.concat(s)
        Function<String, String> greeter = prefix::concat;
        System.out.println(greeter.apply("World"));
        
        // 3. Reference to instance method of arbitrary object of particular type
        // Lambda: s -> s.toUpperCase()
        names.stream()
             .map(String::toUpperCase)
             .forEach(System.out::println);
        
        // 4. Reference to constructor
        // Lambda: () -> new StringBuilder()
        Supplier<StringBuilder> sbSupplier = StringBuilder::new;
        StringBuilder sb = sbSupplier.get();
        sb.append("Test");
        System.out.println(sb);
        
        // More examples
        names.forEach(System.out::println); // Method reference
        // vs
        names.forEach(name -> System.out.println(name)); // Lambda
        
        // Comparing strings
        names.sort(String::compareToIgnoreCase);
        System.out.println("Sorted: " + names);
    }
}
