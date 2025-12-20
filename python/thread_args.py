import threading

def greet(name, greeting="Hello"):
    print(f"{greeting}, {name}!")

# Positional arguments
t1 = threading.Thread(target=greet, args=("Alice",))
t1.start()

# With keyword arguments
t2 = threading.Thread(target=greet, args=("Bob",), kwargs={"greeting": "Hi"})
t2.start()

t1.join()
t2.join()
