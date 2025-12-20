class MyRunnable implements Runnable {
    private String taskName;
    
    public MyRunnable(String name) {
        this.taskName = name;
    }
    
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(taskName + " executing - " + i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class RunnableExample {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable("Task-1"));
        Thread t2 = new Thread(new MyRunnable("Task-2"));
        
        t1.start();
        t2.start();
    }
}
