def uppercase(func):
    def wrapper(*args, **kwargs):
        print("Transforming to upper case..")
        result = func(*args, **kwargs)
        return result.upper()
    return wrapper

def add_exclamation(func):
    def wrapper(*args, **kwargs):
        print("Adding exclaimation..")
        result = func(*args, **kwargs)
        return result + "!!!"
    return wrapper

@uppercase ## applied first in the chain
@add_exclamation ## applied next
def greet(name):
    return f"hello, {name}"

print(greet("Alice"))  # Output: HELLO, ALICE!!!
