public class DeadlockExample {
    private static final Object resource1 = new Object();
    private static final Object resource2 = new Object();
    
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: Holding resource 1");
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                System.out.println("Thread 1: Waiting for resource 2");
                synchronized (resource2) {
                    System.out.println("Thread 1: Holding both resources");
                }
            }
        });
        
        Thread t2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread 2: Holding resource 2");
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                System.out.println("Thread 2: Waiting for resource 1");
                synchronized (resource1) {
                    System.out.println("Thread 2: Holding both resources");
                }
            }
        });
        
        t1.start();
        t2.start();
        
        // This will deadlock! Both threads wait forever
    }
}
