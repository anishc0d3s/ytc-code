class Engine:
    def __init__(self, horsepower):
        self.horsepower = horsepower
    
    def start(self):
        return f"Engine with {self.horsepower}hp started"

class Wheel:
    def __init__(self, size):
        self.size = size

class Car:
    def __init__(self, brand, horsepower, wheel_size):
        self.brand = brand
        self.engine = Engine(horsepower)  # Composition
        self.wheels = [Wheel(wheel_size) for _ in range(4)]
    
    def start(self):
        return f"{self.brand}: {self.engine.start()}"
    
    def wheel_info(self):
        return f"{len(self.wheels)} wheels of size {self.wheels[0].size}"

car = Car("Tesla", 450, 19)
print(car.start())
# print(car.wheels[3].size)
print(car.wheel_info())
