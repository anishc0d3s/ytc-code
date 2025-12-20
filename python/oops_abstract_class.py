from abc import ABC, abstractmethod

class Vehicle(ABC):
    def __init__(self, brand):
        self.brand = brand
    
    @abstractmethod
    def start_engine(self):
        pass
    
    @abstractmethod
    def stop_engine(self):
        pass
    
    def honk(self):
        return "Beep beep!"

### Abstract methods must be implemented in the subclass.
class Car(Vehicle):
    def start_engine(self):
        return f"{self.brand} car engine started"
    
    def stop_engine(self):
        return f"{self.brand} car engine stopped"

class Motorcycle(Vehicle):
    def start_engine(self):
        return f"{self.brand} motorcycle engine roaring"
    
    def stop_engine(self):
        return f"{self.brand} motorcycle engine off"

# vehicle = Vehicle("Generic")  # TypeError: Can't instantiate abstract class

car = Car("Toyota")
bike = Motorcycle("Harley")

print(car.start_engine())
print(bike.honk())
