import java.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;
import java.util.Map;

public class ConcurrentHashMapExample {
    public static void main(String[] args) throws InterruptedException {
        // Regular HashMap - not thread-safe
        Map<String, Integer> regularMap = new HashMap<>();
        
        // ConcurrentHashMap - thread-safe
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        
        // Initial data
        concurrentMap.put("A", 1);
        concurrentMap.put("B", 2);
        concurrentMap.put("C", 3);
        
        // Thread 1: Reads and writes
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                concurrentMap.put("T1-" + i, i);
                concurrentMap.get("A");
            }
        });
        
        // Thread 2: Reads and writes
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                concurrentMap.put("T2-" + i, i);
                concurrentMap.get("B");
            }
        });
        
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
        
        System.out.println("Size: " + concurrentMap.size());
        
        // Atomic operations
        concurrentMap.putIfAbsent("D", 4); // Only adds if key doesn't exist
        System.out.println("D: " + concurrentMap.get("D"));
        
        concurrentMap.putIfAbsent("D", 5); // Won't replace existing value
        System.out.println("D: " + concurrentMap.get("D")); // Still 4
        
        // Compute operations
        concurrentMap.compute("A", (key, value) -> value + 10);
        System.out.println("A after compute: " + concurrentMap.get("A"));
        
        // Merge operation
        concurrentMap.merge("A", 5, (oldVal, newVal) -> oldVal + newVal);
        System.out.println("A after merge: " + concurrentMap.get("A"));
    }
}
