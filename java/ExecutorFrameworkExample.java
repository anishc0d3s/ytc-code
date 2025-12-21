import java.util.concurrent.*;

public class ExecutorFrameworkExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Fixed thread pool
        ExecutorService fixedPool = Executors.newFixedThreadPool(2);
        
        // Cached thread pool (creates new threads as needed)
        ExecutorService cachedPool = Executors.newCachedThreadPool();
        
        // Single thread executor
        ExecutorService singleExecutor = Executors.newSingleThreadExecutor();
        
        // Scheduled executor (for delayed/periodic tasks)
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);
        
        // Example with Callable (returns result)
        Callable<Integer> task = () -> {
            Thread.sleep(1000);
            return 42;
        };
        
        Future<Integer> future = fixedPool.submit(task);
        System.out.println("Task submitted");
        
        // Get result (blocks until complete)
        Integer result = future.get();
        System.out.println("Result: " + result);
        
        // Schedule a task to run after 2 seconds
        scheduledExecutor.schedule(() -> {
            System.out.println("Delayed task executed");
        }, 2, TimeUnit.SECONDS);
        
        // Schedule a task to run periodically
        scheduledExecutor.scheduleAtFixedRate(() -> {
            System.out.println("Periodic task: " + System.currentTimeMillis());
        }, 0, 1, TimeUnit.SECONDS);
        
        Thread.sleep(5000);
        
        fixedPool.shutdown();
        scheduledExecutor.shutdown();
    }
}
