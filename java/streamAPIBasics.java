import java.util.*;
import java.util.stream.*;

public class StreamBasics {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // filter - keep elements matching predicate
        List<Integer> evenNumbers = numbers.stream()
                                           .filter(n -> n % 2 == 0)
                                           .collect(Collectors.toList());
        System.out.println("Even numbers: " + evenNumbers);
        
        // map - transform elements
        List<Integer> squared = numbers.stream()
                                       .map(n -> n * n)
                                       .collect(Collectors.toList());
        System.out.println("Squared: " + squared);
        
        // reduce - combine elements into single result
        int sum = numbers.stream()
                        .reduce(0, (a, b) -> a + b);
        System.out.println("Sum: " + sum);
        
        // Alternative using sum()
        int sum2 = numbers.stream()
                         .mapToInt(Integer::intValue)
                         .sum();
        System.out.println("Sum (method 2): " + sum2);
        
        // count
        long count = numbers.stream()
                           .filter(n -> n > 5)
                           .count();
        System.out.println("Numbers > 5: " + count);
        
        // distinct - remove duplicates
        List<Integer> withDuplicates = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 5, 5);
        List<Integer> unique = withDuplicates.stream()
                                            .distinct()
                                            .collect(Collectors.toList());
        System.out.println("Unique: " + unique);
        
        // sorted
        List<Integer> sorted = numbers.stream()
                                     .sorted(Comparator.reverseOrder())
                                     .collect(Collectors.toList());
        System.out.println("Sorted descending: " + sorted);
        
        // limit and skip
        List<Integer> limited = numbers.stream()
                                      .skip(3)
                                      .limit(4)
                                      .collect(Collectors.toList());
        System.out.println("Skip 3, take 4: " + limited);
        
        // anyMatch, allMatch, noneMatch
        boolean hasEven = numbers.stream().anyMatch(n -> n % 2 == 0);
        boolean allPositive = numbers.stream().allMatch(n -> n > 0);
        boolean noneNegative = numbers.stream().noneMatch(n -> n < 0);
        
        System.out.println("Has even: " + hasEven);
        System.out.println("All positive: " + allPositive);
        System.out.println("None negative: " + noneNegative);
        
        // findFirst and findAny
        Optional<Integer> first = numbers.stream()
                                        .filter(n -> n > 5)
                                        .findFirst();
        first.ifPresent(n -> System.out.println("First number > 5: " + n));
        
        // Creating streams
        Stream<String> streamFromValues = Stream.of("a", "b", "c");
        Stream<Integer> streamFromArray = Arrays.stream(new Integer[]{1, 2, 3});
        IntStream rangeStream = IntStream.range(1, 5); // 1, 2, 3, 4
        Stream<Double> infiniteStream = Stream.generate(Math::random).limit(5);
    }
}
