from dataclasses import dataclass

### looks like dataclasses don't enforce type validation
### they are purely use for type annotations to make the code more readable
@dataclass
class Product:
    name: str
    price: float
    quantity: int = 0  # Default value
    
    def total_value(self):
        return self.price * self.quantity

product = Product("Laptop", 999.99, 5)
print(product)  # Product(name='Laptop', price=999.99, quantity=5)
print(product.total_value())  # 4999.95
