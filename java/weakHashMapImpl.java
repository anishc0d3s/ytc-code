import java.util.Map;
import java.util.WeakHashMap;
import java.util.HashMap;

class LargeObject {
    private String data;
    
    public LargeObject(String data) {
        this.data = data;
    }
    
    @Override
    protected void finalize() {
        System.out.println("Object finalized: " + data);
    }
    
    @Override
    public String toString() {
        return data;
    }
}

public class WeakHashMapExample {
    public static void main(String[] args) throws InterruptedException {
        // Regular HashMap - keeps strong references
        Map<LargeObject, String> strongMap = new HashMap<>();
        
        // WeakHashMap - uses weak references for keys
        Map<LargeObject, String> weakMap = new WeakHashMap<>();
        
        LargeObject key1 = new LargeObject("Key1");
        LargeObject key2 = new LargeObject("Key2");
        
        strongMap.put(key1, "Value1");
        weakMap.put(key2, "Value2");
        
        System.out.println("Before GC:");
        System.out.println("StrongMap size: " + strongMap.size());
        System.out.println("WeakMap size: " + weakMap.size());
        
        // Remove strong references
        key1 = null;
        key2 = null;
        
        // Suggest garbage collection
        System.gc();
        Thread.sleep(1000);
        
        System.out.println("\nAfter GC:");
        System.out.println("StrongMap size: " + strongMap.size()); // Still 1
        System.out.println("WeakMap size: " + weakMap.size());     // 0 (entry removed)
    }
}
