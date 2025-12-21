class SharedResource {
    private int data;
    private boolean hasData = false;
    
    public synchronized void produce(int value) {
        while (hasData) {
            try {
                wait(); // Wait until data is consumed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        this.data = value;
        hasData = true;
        System.out.println("Produced: " + value);
        notify(); // Notify consumer
    }
    
    public synchronized int consume() {
        while (!hasData) {
            try {
                wait(); // Wait until data is produced
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        hasData = false;
        System.out.println("Consumed: " + data);
        notify(); // Notify producer
        return data;
    }
}

public class ProducerConsumerExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        
        // Producer thread
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                resource.produce(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        // Consumer thread
        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                resource.consume();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        producer.start();
        consumer.start();
    }
}
