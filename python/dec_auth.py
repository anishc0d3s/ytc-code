def require_auth(func):
    def wrapper(user, *args, **kwargs):
        if not user.get("is_authenticated"):
            print("❌ Access denied. Please log in.")
            return None
        print("✅ Access granted")
        return func(user, *args, **kwargs)
    return wrapper

@require_auth
def view_profile(user):
    return f"Profile for {user['name']}"

@require_auth
def delete_account(user):
    return f"Account {user['name']} deleted"

user1 = {"name": "Alice", "is_authenticated": True}
user2 = {"name": "Bob", "is_authenticated": False}

print(view_profile(user1))
print(delete_account(user2))
