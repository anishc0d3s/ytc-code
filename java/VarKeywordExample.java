import java.util.*;

public class VarKeywordExample {
    public static void main(String[] args) {
        // Traditional declarations
        String name = "John";
        List<String> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        
        // Using var (Java 10+)
        var inferredName = "John"; // String
        var inferredList = new ArrayList<String>(); // ArrayList<String>
        var inferredMap = new HashMap<String, Integer>(); // HashMap<String, Integer>
        
        // Works with complex types
        var stream = list.stream()
                        .filter(s -> s.length() > 3)
                        .map(String::toUpperCase);
        
        // Loop variable
        var numbers = List.of(1, 2, 3, 4, 5);
        for (var number : numbers) {
            System.out.println(number);
        }
        
        // Lambda parameters (Java 11)
        numbers.stream()
              .map((var n) -> n * 2)
              .forEach(System.out::println);
        
        // Cannot use var without initializer
        // var x; // Compilation error
        
        // Cannot use var with null
        // var y = null; // Compilation error
        
        // Cannot use for fields, method parameters, or return types
        // Only for local variables
    }
}
