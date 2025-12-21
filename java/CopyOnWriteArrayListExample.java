import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListExample {
    public static void main(String[] args) throws InterruptedException {
        // CopyOnWriteArrayList - safe for concurrent iteration and modification
        List<String> cowList = new CopyOnWriteArrayList<>();
        
        cowList.add("Apple");
        cowList.add("Banana");
        cowList.add("Cherry");
        
        // Thread 1: Iterates over list
        Thread reader = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                for (String item : cowList) {
                    System.out.println("Reading: " + item);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        
        // Thread 2: Modifies list during iteration
        Thread writer = new Thread(() -> {
            try {
                Thread.sleep(200);
                cowList.add("Date");
                System.out.println("Added Date");
                Thread.sleep(200);
                cowList.add("Elderberry");
                System.out.println("Added Elderberry");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        reader.start();
        writer.start();
        
        reader.join();
        writer.join();
        
        // Regular ArrayList would throw ConcurrentModificationException
        
        System.out.println("\nFinal list: " + cowList);
    }
}
