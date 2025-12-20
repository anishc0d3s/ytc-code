public class PatternMatchingInstanceof {
    public static void main(String[] args) {
        Object obj = "Hello World";
        
        // Traditional instanceof
        if (obj instanceof String) {
            String str = (String) obj;
            System.out.println("Length: " + str.length());
        }
        
        // Pattern matching (Java 16+)
        if (obj instanceof String str) {
            System.out.println("Length: " + str.length());
            // str is in scope here
        }
        
        // With logical operators
        if (obj instanceof String str && str.length() > 5) {
            System.out.println("Long string: " + str);
        }
        
        // Practical example
        Object[] objects = {
            "Hello",
            42,
            3.14,
            Arrays.asList(1, 2, 3),
            null
        };
        
        for (Object o : objects) {
            printInfo(o);
        }
    }
    
    static void printInfo(Object obj) {
        if (obj instanceof String s) {
            System.out.println("String: " + s + ", length: " + s.length());
        } else if (obj instanceof Integer i) {
            System.out.println("Integer: " + i + ", doubled: " + (i * 2));
        } else if (obj instanceof Double d) {
            System.out.println("Double: " + d + ", squared: " + (d * d));
        } else if (obj instanceof List<?> list) {
            System.out.println("List with " + list.size() + " elements");
        } else if (obj == null) {
            System.out.println("null value");
        } else {
            System.out.println("Unknown type: " + obj.getClass());
        }
    }
}
