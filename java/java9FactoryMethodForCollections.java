import java.util.*;

public class CollectionFactoryMethods {
    public static void main(String[] args) {
        // Immutable List
        List<String> list = List.of("Apple", "Banana", "Cherry");
        System.out.println("List: " + list);
        // list.add("Date"); // UnsupportedOperationException
        
        // Immutable Set
        Set<Integer> set = Set.of(1, 2, 3, 4, 5);
        System.out.println("Set: " + set);
        
        // Immutable Map
        Map<String, Integer> map = Map.of(
            "One", 1,
            "Two", 2,
            "Three", 3
        );
        System.out.println("Map: " + map);
        
        // Map with more than 10 entries
        Map<String, Integer> largeMap = Map.ofEntries(
            Map.entry("A", 1),
            Map.entry("B", 2),
            Map.entry("C", 3),
            Map.entry("D", 4),
            Map.entry("E", 5),
            Map.entry("F", 6),
            Map.entry("G", 7),
            Map.entry("H", 8),
            Map.entry("I", 9),
            Map.entry("J", 10),
            Map.entry("K", 11)
        );
        System.out.println("Large map size: " + largeMap.size());
        
        // All these collections are immutable
        try {
            list.add("Date");
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify immutable list");
        }
    }
}
