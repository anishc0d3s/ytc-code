public class LambdaThreadExample {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Lambda thread - " + i);
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        t1.start();
    }
}

public class ThreadLifecycle {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("Thread running");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        System.out.println("State after creation: " + thread.getState()); // NEW
        
        thread.start();
        System.out.println("State after start: " + thread.getState()); // RUNNABLE
        
        Thread.sleep(100);
        System.out.println("State while sleeping: " + thread.getState()); // TIMED_WAITING
        
        thread.join(); // Wait for thread to finish
        System.out.println("State after completion: " + thread.getState()); // TERMINATED
    }
}
