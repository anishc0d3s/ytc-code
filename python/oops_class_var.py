class Employee:
    company = "TechCorp"  # Class variable (shared by all instances)
    employee_count = 0
    
    def __init__(self, name, salary):
        self.name = name      # Instance variable (unique to each instance)
        self.salary = salary
        Employee.employee_count += 1
    
    def display(self):
        return f"{self.name} works at {Employee.company}"

emp1 = Employee("Alice", 50000)
emp2 = Employee("Bob", 60000)

print(emp1.company)  # TechCorp
print(emp2.company)  # TechCorp
print(Employee.employee_count)  # 2

# Changing class variable affects all instances
Employee.company = "NewTech"
print(emp1.display())  # Alice works at NewTech
