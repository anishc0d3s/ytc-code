class MathOperations:
    pi = 3.14159
    
    def __init__(self, value):
        self.value = value
    
    # Instance method (has access to self)
    def square(self):
        return self.value ** 2
    
    # Class method (has access to cls, can modify class state)
    @classmethod
    def circle_area(cls, radius):
        return cls.pi * radius ** 2
    
    # Static method (no access to self or cls, just a utility function)
    @staticmethod
    def add(a, b):
        return a + b

math = MathOperations(5)
print(math.square())                    # 25 (instance method)
print(MathOperations.circle_area(10))   # 314.159 (class method)
print(MathOperations.add(3, 7))         # 10 (static method)
