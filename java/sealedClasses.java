// Sealed class - only specified classes can extend it
sealed class Shape permits Circle, Rectangle, Triangle {
    abstract double area();
}

// Permitted subclasses must be final, sealed, or non-sealed
final class Circle extends Shape {
    private final double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    double area() {
        return Math.PI * radius * radius;
    }
}

final class Rectangle extends Shape {
    private final double width;
    private final double height;
    
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    @Override
    double area() {
        return width * height;
    }
}

// Non-sealed allows further extension
non-sealed class Triangle extends Shape {
    private final double base;
    private final double height;
    
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    
    @Override
    double area() {
        return 0.5 * base * height;
    }
}

// Can now extend Triangle
class EquilateralTriangle extends Triangle {
    public EquilateralTriangle(double side) {
        super(side, side * Math.sqrt(3) / 2);
    }
}

// Sealed interface
sealed interface PaymentMethod permits CreditCard, DebitCard, Cash {}

final class CreditCard implements PaymentMethod {
    private String cardNumber;
}

final class DebitCard implements PaymentMethod {
    private String cardNumber;
}

final class Cash implements PaymentMethod {
    private double amount;
}

public class SealedClassesExample {
    public static void main(String[] args) {
        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(4, 6);
        Shape triangle = new Triangle(3, 4);
        
        System.out.println("Circle area: " + circle.area());
        System.out.println("Rectangle area: " + rectangle.area());
        System.out.println("Triangle area: " + triangle.area());
        
        // Pattern matching with sealed classes (exhaustive)
        printArea(circle);
        printArea(rectangle);
        printArea(triangle);
    }
    
    // Compiler knows all possible subtypes
    static void printArea(Shape shape) {
        double area = switch (shape) {
            case Circle c -> c.area();
            case Rectangle r -> r.area();
            case Triangle t -> t.area();
            // No default needed - exhaustive!
        };
        System.out.println("Area: " + area);
    }
}
