from pydantic import BaseModel, EmailStr, ValidationError
from typing import Optional


class User(BaseModel):
    id: int
    name: str
    email: EmailStr
    age: Optional[int] = None # this makes the field truly optional in the constructor
    is_active: bool = True


user = User(id="123", name="Jane Doe", email="jane@example.com")
print(user.id)
print(user.is_active)

print(user.model_dump())

try:
    # 'age' expects an int, but receives a string that can't be converted
    invalid_user = User(id=1, name="John", email="email@test.com", age="30") ## pydantic auto-converts the type
    print(invalid_user.age)
except ValidationError as e:
    print('ValidationError')
    print(e.json())
    # This will show errors for both the invalid email and the invalid age
