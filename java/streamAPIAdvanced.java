import java.util.*;
import java.util.stream.*;

public class AdvancedStreamOperations {
    public static void main(String[] args) {
        // flatMap - flatten nested structures
        List<List<Integer>> nestedList = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(4, 5),
            Arrays.asList(6, 7, 8, 9)
        );
        
        List<Integer> flattened = nestedList.stream()
                                           .flatMap(List::stream)
                                           .collect(Collectors.toList());
        System.out.println("Flattened: " + flattened);
        
        // peek - perform action without consuming stream
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> result = numbers.stream()
                                     .peek(n -> System.out.println("Processing: " + n))
                                     .map(n -> n * 2)
                                     .peek(n -> System.out.println("After map: " + n))
                                     .collect(Collectors.toList());
        
        // Grouping
        List<String> words = Arrays.asList("apple", "banana", "cherry", "apricot", "blueberry");
        Map<Character, List<String>> groupedByFirstLetter = words.stream()
            .collect(Collectors.groupingBy(s -> s.charAt(0)));
        System.out.println("Grouped: " + groupedByFirstLetter);
        
        // Partitioning
        Map<Boolean, List<Integer>> partitioned = numbers.stream()
            .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("Even: " + partitioned.get(true));
        System.out.println("Odd: " + partitioned.get(false));
        
        // Collectors.joining
        String joined = words.stream()
                            .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("Joined: " + joined);
        
        // Statistics
        IntSummaryStatistics stats = numbers.stream()
                                           .mapToInt(Integer::intValue)
                                           .summaryStatistics();
        System.out.println("Count: " + stats.getCount());
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Max: " + stats.getMax());
        System.out.println("Average: " + stats.getAverage());
    }
}
