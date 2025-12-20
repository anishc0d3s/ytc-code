def log_calls(func):
    def wrapper(*args, **kwargs):
        print(f"📞 Calling: {func.__name__}")
        print(f"   Arguments: {args}, {kwargs}")
        result = func(*args, **kwargs)
        print(f"   Returned: {result}")
        return result
    return wrapper

@log_calls
def multiply(x, y):
    return x * y

multiply(4, 5)

