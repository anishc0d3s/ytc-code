import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Producer implements Runnable {
    private BlockingQueue<Integer> queue;
    
    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }
    
    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Producing: " + i);
                queue.put(i); // Blocks if queue is full
                Thread.sleep(500);
            }
            queue.put(-1); // Poison pill to signal completion
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer implements Runnable {
    private BlockingQueue<Integer> queue;
    
    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                Integer value = queue.take(); // Blocks if queue is empty
                if (value == -1) {
                    break; // Poison pill received
                }
                System.out.println("Consuming: " + value);
                Thread.sleep(1000); // Simulate slow consumer
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class BlockingQueueExample {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
        
        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));
        
        producer.start();
        consumer.start();
    }
}
