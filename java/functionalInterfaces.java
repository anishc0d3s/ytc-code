import java.util.function.*;
import java.util.Arrays;
import java.util.List;

public class FunctionalInterfacesExample {
    public static void main(String[] args) {
        // Predicate<T> - takes one argument, returns boolean
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Is 4 even? " + isEven.test(4));
        System.out.println("Is 5 even? " + isEven.test(5));
        
        // Function<T, R> - takes one argument, returns result
        Function<String, Integer> stringLength = s -> s.length();
        System.out.println("Length of 'Hello': " + stringLength.apply("Hello"));
        
        // Consumer<T> - takes one argument, returns nothing
        Consumer<String> printer = s -> System.out.println("Consuming: " + s);
        printer.accept("Test");
        
        // Supplier<T> - takes no arguments, returns result
        Supplier<Double> randomSupplier = () -> Math.random();
        System.out.println("Random number: " + randomSupplier.get());
        
        // BiFunction<T, U, R> - takes two arguments, returns result
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        System.out.println("5 * 3 = " + multiply.apply(5, 3));
        
        // UnaryOperator<T> - special case of Function where input and output are same type
        UnaryOperator<Integer> square = n -> n * n;
        System.out.println("Square of 7: " + square.apply(7));
        
        // BinaryOperator<T> - special case of BiFunction where both inputs and output are same type
        BinaryOperator<Integer> max = (a, b) -> a > b ? a : b;
        System.out.println("Max of 10 and 15: " + max.apply(10, 15));
        
        // Chaining predicates
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isEvenAndPositive = isEven.and(isPositive);
        System.out.println("Is 4 even and positive? " + isEvenAndPositive.test(4));
        System.out.println("Is -4 even and positive? " + isEvenAndPositive.test(-4));
        
        // Chaining functions
        Function<String, String> addExclamation = s -> s + "!";
        Function<String, String> toUpperCase = s -> s.toUpperCase();
        Function<String, String> combined = addExclamation.andThen(toUpperCase);
        System.out.println(combined.apply("hello")); // HELLO!
    }
}
