public class YieldExample {
    public static void main(String[] args) {
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Producer: " + i);
                Thread.yield(); // Gives hint to scheduler
            }
        });
        
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Consumer: " + i);
                Thread.yield();
            }
        });
        
        producer.start();
        consumer.start();
    }
}
