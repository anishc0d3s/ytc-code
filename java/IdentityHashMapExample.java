import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

public class IdentityHashMapExample {
    public static void main(String[] args) {
        Map<String, Integer> regularMap = new HashMap<>();
        Map<String, Integer> identityMap = new IdentityHashMap<>();
        
        String key1 = new String("Key");
        String key2 = new String("Key");
        
        // Regular HashMap uses equals()
        regularMap.put(key1, 1);
        regularMap.put(key2, 2);
        System.out.println("HashMap size: " + regularMap.size()); // 1 (key1.equals(key2))
        System.out.println("HashMap value: " + regularMap.get(key1)); // 2 (overwritten)
        
        // IdentityHashMap uses ==
        identityMap.put(key1, 1);
        identityMap.put(key2, 2);
        System.out.println("IdentityHashMap size: " + identityMap.size()); // 2 (key1 != key2)
        System.out.println("IdentityHashMap value for key1: " + identityMap.get(key1)); // 1
        System.out.println("IdentityHashMap value for key2: " + identityMap.get(key2)); // 2
        
        // String literals (same reference)
        identityMap.put("Test", 100);
        identityMap.put("Test", 200);
        System.out.println("IdentityHashMap with literals: " + identityMap.size()); // Still 2 + 1 = 3
        System.out.println("Value for 'Test': " + identityMap.get("Test")); // 200 (overwritten)
    }
}
