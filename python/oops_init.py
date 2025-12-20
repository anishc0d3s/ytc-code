class Person:
    def __init__(self, name, age, city="Unknown"):
        self.name = name
        self.age = age
        self.city = city
        print(f"Person object created: {name}")
    
    def introduce(self):
        return f"Hi, I'm {self.name}, {self.age} years old from {self.city}"

person1 = Person("Alice", 30, "New York")
person2 = Person("Bob", 25)  # Uses default city

print(person1.introduce())
print(person2.introduce())
