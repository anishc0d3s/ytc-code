import java.util.*;
import java.util.stream.*;
import java.nio.file.*;
import java.io.IOException;

public class CreatingStreams {
    public static void main(String[] args) throws IOException {
        // 1. From collections
        List<String> list = Arrays.asList("a", "b", "c");
        Stream<String> streamFromList = list.stream();
        
        // 2. From arrays
        String[] array = {"x", "y", "z"};
        Stream<String> streamFromArray = Arrays.stream(array);
        
        // 3. From values
        Stream<String> streamFromValues = Stream.of("one", "two", "three");
        
        // 4. Empty stream
        Stream<String> emptyStream = Stream.empty();
        
        // 5. From builder
        Stream<String> streamBuilder = Stream.<String>builder()
            .add("a")
            .add("b")
            .add("c")
            .build();
        
        // 6. Infinite streams with generate
        Stream<Double> randomNumbers = Stream.generate(Math::random)
                                            .limit(5);
        randomNumbers.forEach(System.out::println);
        
        // 7. Infinite streams with iterate
        Stream<Integer> evenNumbers = Stream.iterate(0, n -> n + 2)
                                           .limit(10);
        evenNumbers.forEach(System.out::println);
        
        // 8. Range streams
        IntStream intStream = IntStream.range(1, 5); // 1, 2, 3, 4
        IntStream intStreamClosed = IntStream.rangeClosed(1, 5); // 1, 2, 3, 4, 5
        
        // 9. From strings
        IntStream charStream = "Hello".chars();
        charStream.forEach(c -> System.out.print((char) c + " "));
        System.out.println();
        
        // 10. From files
        Stream<String> lines = Files.lines(Paths.get("test.txt"));
        // lines.forEach(System.out::println);
        
        // 11. Parallel stream
        Stream<String> parallelStream = list.parallelStream();
        
        // 12. Concat streams
        Stream<String> combined = Stream.concat(
            Stream.of("a", "b"),
            Stream.of("c", "d")
        );
        combined.forEach(System.out::println);
    }
}
