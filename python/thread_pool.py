from concurrent.futures import ThreadPoolExecutor
import time

def task(n):
    print(f"Processing task {n}")
    time.sleep(1)
    return n * n

# Create a pool of three worker threads
with ThreadPoolExecutor(max_workers=3) as executor:
    futures = [executor.submit(task, i) for i in range(5)]


    # Get results
    for future in futures:
        result = future.result()
        print(f"Result: {result}")

