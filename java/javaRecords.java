// Traditional class
class PersonOld {
    private final String name;
    private final int age;
    
    public PersonOld(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonOld)) return false;
        PersonOld person = (PersonOld) o;
        return age == person.age && name.equals(person.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
    
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}

// Record (Java 16+) - equivalent to above
record Person(String name, int age) {
    // Automatically generates:
    // - Constructor
    // - Getters (name(), age())
    // - equals(), hashCode(), toString()
}

// Record with validation
record Employee(String name, int age, double salary) {
    // Compact constructor
    public Employee {
        if (age < 18) {
            throw new IllegalArgumentException("Age must be at least 18");
        }
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
    }
}

// Record with custom methods
record Point(int x, int y) {
    public double distanceFromOrigin() {
        return Math.sqrt(x * x + y * y);
    }
    
    public Point translate(int dx, int dy) {
        return new Point(x + dx, y + dy);
    }
}

public class RecordsExample {
    public static void main(String[] args) {
        Person person = new Person("Alice", 30);
        System.out.println(person.name()); // Accessor method
        System.out.println(person.age());
        System.out.println(person); // toString()
        
        Person person2 = new Person("Alice", 30);
        System.out.println("Equals: " + person.equals(person2)); // true
        
        // Employee with validation
        try {
            Employee emp = new Employee("Bob", 16, 50000);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // Point with custom methods
        Point p1 = new Point(3, 4);
        System.out.println("Distance: " + p1.distanceFromOrigin());
        Point p2 = p1.translate(1, 1);
        System.out.println("Translated: " + p2);
        
        // Records are immutable
        // person.name = "Bob"; // Compilation error - no setter
    }
}
