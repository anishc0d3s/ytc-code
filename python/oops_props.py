class Temperature:
    def __init__(self, celsius):
        self._celsius = celsius
    
    @property
    def celsius(self):
        return self._celsius
    
    @celsius.setter
    def celsius(self, value):
        if value < -273.15:
            raise ValueError("Temperature cannot be below absolute zero")
        self._celsius = value
    
    @property
    def fahrenheit(self):
        return (self._celsius * 9/5) + 32
    
    @fahrenheit.setter
    def fahrenheit(self, value):
        self.celsius = (value - 32) * 5/9

temp = Temperature(25)
print(f"{temp.celsius}°C = {temp.fahrenheit}°F")

temp.celsius = 0
print(f"{temp.celsius}°C = {temp.fahrenheit}°F")

temp.fahrenheit = 212 # this will update the celsius value, by calling @fahrenheit.setter
print(f"{temp.celsius}°C = {temp.fahrenheit}°F") # temp.celsius returns _celsius. temp.fahrenheit re-calculates it's from the celsius value.


