class CountCalls:
    ## Takes in the wrapper function
    def __init__(self, func):
        self.func = func
        self.count = 0
    
    ## The arguments that will be passed to the wrapper function
    ### This is the function that gets called.
    def __call__(self, *args, **kwargs):
        self.count += 1
        print(f"Call {self.count} to {self.func.__name__}")
        return self.func(*args, **kwargs)

@CountCalls
def say_hello():
    print("Hello!")

say_hello()
say_hello()
say_hello()
