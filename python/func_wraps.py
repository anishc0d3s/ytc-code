from functools import wraps

def my_decorator(func):
    @wraps(func)  # Preserves func's __name__, __doc__, etc.
    def wrapper(*args, **kwargs):
        """Wrapper documentation"""
        return func(*args, **kwargs)
    return wrapper

@my_decorator
def important_function():
    """This function does important things"""
    pass

print(important_function.__name__)  # Output: important_function
print(important_function.__doc__)   # Output: This function does important things
