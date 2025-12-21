import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {
        // Creating Optional
        Optional<String> empty = Optional.empty();
        Optional<String> nonEmpty = Optional.of("Hello");
        Optional<String> nullable = Optional.ofNullable(null);
        
        // isPresent and ifPresent
        if (nonEmpty.isPresent()) {
            System.out.println("Value: " + nonEmpty.get());
        }
        
        nonEmpty.ifPresent(value -> System.out.println("Value via ifPresent: " + value));
        
        // orElse - return value or default
        String value1 = empty.orElse("Default Value");
        System.out.println("Empty or else: " + value1);
        
        // orElseGet - return value or compute default
        String value2 = empty.orElseGet(() -> "Computed Default");
        System.out.println("Empty or else get: " + value2);
        
        // orElseThrow
        try {
            String value3 = empty.orElseThrow(() -> new RuntimeException("No value present"));
        } catch (RuntimeException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        
        // map - transform value if present
        Optional<Integer> length = nonEmpty.map(String::length);
        length.ifPresent(len -> System.out.println("Length: " + len));
        
        // flatMap - for chaining Optional-returning functions
        Optional<String> result = nonEmpty.flatMap(s -> Optional.of(s.toUpperCase()));
        result.ifPresent(System.out::println);
        
        // filter
        Optional<String> filtered = nonEmpty.filter(s -> s.length() > 3);
        System.out.println("Filtered present: " + filtered.isPresent());
        
        // Practical example: avoiding null checks
        String name = getName();
        
        // Traditional way
        if (name != null) {
            String upper = name.toUpperCase();
            System.out.println(upper);
        }
        
        // Optional way
        Optional.ofNullable(name)
                .map(String::toUpperCase)
                .ifPresent(System.out::println);
    }
    
    private static String getName() {
        return Math.random() > 0.5 ? "John" : null;
    }
}
