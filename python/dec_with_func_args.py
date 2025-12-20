### Decorator returns the wrapper function
def my_decorator(func):
    ## Wrapper function called with arguments
    ## Returns result
    def wrapper(*args, **kwargs):
        print(f"Calling {func.__name__} with args: {args}, kwargs: {kwargs}")
        result = func(*args, **kwargs)
        return result
    return wrapper

@my_decorator
def add(a, b):
    return a + b

@my_decorator
def greet(name, greeting="Hello"):
    return f"{greeting} {name}"

print(add(5, 3))
print(greet("Alice", greeting="Hi"))
