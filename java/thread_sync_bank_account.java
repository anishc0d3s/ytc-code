class BankAccount {
    private double balance = 1000;
    private final Object lock = new Object();
    
    public void withdraw(double amount) {
        synchronized (lock) {
            if (balance >= amount) {
                System.out.println(Thread.currentThread().getName() + 
                                   " withdrawing " + amount);
                try {
                    Thread.sleep(100); // Simulate processing time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance -= amount;
                System.out.println(Thread.currentThread().getName() + 
                                   " completed. Balance: " + balance);
            } else {
                System.out.println(Thread.currentThread().getName() + 
                                   " insufficient funds");
            }
        }
    }
    
    public double getBalance() {
        synchronized (lock) {
            return balance;
        }
    }
}

public class SynchronizedBlockExample {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        
        Runnable task = () -> {
            for (int i = 0; i < 3; i++) {
                account.withdraw(200);
            }
        };
        
        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        
        t1.start();
        t2.start();
    }
}
