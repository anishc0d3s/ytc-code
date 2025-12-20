import time
from functools import wraps

def rate_limit(max_calls, period):
    def decorator(func):
        calls = []
        
        @wraps(func)
        def wrapper(*args, **kwargs):
            now = time.time()
            # Remove old calls outside the time window
            calls[:] = [call for call in calls if call > now - period]
            
            if len(calls) >= max_calls:
                print(f"⚠️ Rate limit exceeded. Try again in {period - (now - calls[0]):.1f} seconds")
                return None
            
            calls.append(now)
            return func(*args, **kwargs)
        
        return wrapper
    return decorator

@rate_limit(max_calls=3, period=10)
def api_call():
    print("✅ API call successful")
    return "Data"

# Try calling multiple times
for i in range(5):
    api_call()
    time.sleep(1)
