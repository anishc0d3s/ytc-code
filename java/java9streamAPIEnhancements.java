import java.util.*;
import java.util.stream.*;

public class StreamEnhancements {
    public static void main(String[] args) {
        // takeWhile - takes elements while predicate is true
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        List<Integer> taken = numbers.stream()
                                    .takeWhile(n -> n < 5)
                                    .collect(Collectors.toList());
        System.out.println("TakeWhile: " + taken); // [1, 2, 3, 4]
        
        // dropWhile - drops elements while predicate is true
        List<Integer> dropped = numbers.stream()
                                      .dropWhile(n -> n < 5)
                                      .collect(Collectors.toList());
        System.out.println("DropWhile: " + dropped); // [5, 6, 7, 8, 9, 10]
        
        // ofNullable - creates stream of 0 or 1 element
        Stream<String> stream1 = Stream.ofNullable("Hello");
        Stream<String> stream2 = Stream.ofNullable(null);
        
        System.out.println("Non-null count: " + stream1.count()); // 1
        System.out.println("Null count: " + stream2.count()); // 0
        
        // iterate with predicate
        List<Integer> generated = Stream.iterate(0, n -> n < 10, n -> n + 2)
                                       .collect(Collectors.toList());
        System.out.println("Generated: " + generated); // [0, 2, 4, 6, 8]
    }
}
