# A decorator takes in a function as an arg
# and returns a wrapper function.
# It decorates the function passed as an argument.

### Decorator
def my_decorator(func):
    def wrapper():
        print("Before function call")
        func()
        print("After function call")
    return wrapper

# Function to be decorated
def say_hello():
    print("Hello!")

# Apply decorator manually
say_hello = my_decorator(say_hello)
say_hello()
