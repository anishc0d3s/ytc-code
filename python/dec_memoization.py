def memoize(func):
    cache = {}
    def wrapper(*args):
        if args not in cache:
            print(f"Computing {func.__name__}{args}")
            ## When the function returns, we cache the result.
            ## The function that returns is the last recursive call.
            ## which is f(0). So, we get a cache entry of cache[0] = 0
            ## followed by (1, 1), (2,1), etc.
            cache[args] = func(*args)
            print(cache)
        else:
            print(f"Using cached result for {func.__name__}{args}")
        return cache[args]
    return wrapper


### Everytime the function fibonacci is called,
### the decorator is instantiated, hence we see the calls to each function
### printing "Computing".
### Since this function is recursive, the behavior is a bit different but the
### the inner-workings of calling a decorator remains the same.
@memoize
def fibonacci(n):
    if n < 2:
        return n
    return fibonacci(n-1) + fibonacci(n-2)

print(fibonacci(5))
print(fibonacci(5))  # Uses cache
