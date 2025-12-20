public class DeadlockPrevention {
    private static final Object resource1 = new Object();
    private static final Object resource2 = new Object();
    
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: Holding resource 1");
                
                synchronized (resource2) {
                    System.out.println("Thread 1: Holding both resources");
                }
            }
        });
        
        Thread t2 = new Thread(() -> {
            // Both threads acquire locks in same order - prevents deadlock
            synchronized (resource1) {
                System.out.println("Thread 2: Holding resource 1");
                
                synchronized (resource2) {
                    System.out.println("Thread 2: Holding both resources");
                }
            }
        });
        
        t1.start();
        t2.start();
    }
}
