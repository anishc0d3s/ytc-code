import java.util.concurrent.*;

public class BlockingQueueTypes {
    public static void main(String[] args) throws InterruptedException {
        // ArrayBlockingQueue - bounded FIFO queue backed by array
        BlockingQueue<String> arrayQueue = new ArrayBlockingQueue<>(10);
        
        // LinkedBlockingQueue - optionally bounded FIFO queue backed by linked nodes
        BlockingQueue<String> linkedQueue = new LinkedBlockingQueue<>(100);
        
        // PriorityBlockingQueue - unbounded queue with priority ordering
        BlockingQueue<Integer> priorityQueue = new PriorityBlockingQueue<>();
        priorityQueue.put(5);
        priorityQueue.put(1);
        priorityQueue.put(3);
        System.out.println(priorityQueue.take()); // 1 (highest priority)
        
        // DelayQueue - elements can only be taken when delay has expired
        DelayQueue<DelayedTask> delayQueue = new DelayQueue<>();
        
        // SynchronousQueue - queue with no capacity (direct handoff)
        BlockingQueue<String> synchronousQueue = new SynchronousQueue<>();
        
        // LinkedTransferQueue - unbounded queue based on linked nodes
        TransferQueue<String> transferQueue = new LinkedTransferQueue<>();
    }
}

class DelayedTask implements Delayed {
    private String name;
    private long delayTime;
    
    public DelayedTask(String name, long delayInMillis) {
        this.name = name;
        this.delayTime = System.currentTimeMillis() + delayInMillis;
    }
    
    @Override
    public long getDelay(TimeUnit unit) {
        long diff = delayTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }
    
    @Override
    public int compareTo(Delayed o) {
        return Long.compare(this.delayTime, ((DelayedTask) o).delayTime);
    }
    
    @Override
    public String toString() {
        return name;
    }
}
