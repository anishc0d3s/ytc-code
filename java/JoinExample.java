
public class JoinExample {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("Thread 1 - " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("Thread 2 - " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        t1.start();
        
        try {
            t1.join(); // Main thread waits for t1 to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        t2.start(); // t2 starts only after t1 completes
        
        System.out.println("Main thread finished");
    }
}
