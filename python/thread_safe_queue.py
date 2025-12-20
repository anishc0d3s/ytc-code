import threading
import queue
import time

def producer(q):
    for i in range(5):
        item = f"Item {i}"
        q.put(item) # put item in queue
        print(f"Produced: {item}")
        time.sleep(0.5) # sleep

def consumer(q):
    while True:
        item = q.get() # get item from queue
        if item is None:  # Poison pill to stop consumer
            break
        print(f"Consumed: {item}")
        time.sleep(1)
        q.task_done() # signal task is done

q = queue.Queue() # initialize queue

# initialize threads. pass the queue to both consumer and producer
producer_thread = threading.Thread(target=producer, args=(q,))
consumer_thread = threading.Thread(target=consumer, args=(q,))

producer_thread.start()
consumer_thread.start()

producer_thread.join()

# print("waiting for consumer thread to finish")
q.put(None)  # Signal consumer to stop
consumer_thread.join()
