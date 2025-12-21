public class SwitchExpressions {
    public static void main(String[] args) {
        // Traditional switch statement
        int day = 3;
        String dayType;
        switch (day) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                dayType = "Weekday";
                break;
            case 6:
            case 7:
                dayType = "Weekend";
                break;
            default:
                dayType = "Invalid";
                break;
        }
        System.out.println("Traditional: " + dayType);
        
        // Switch expression (Java 12+)
        // String dayType2 = switch (day) {
        //     case 1, 2, 3, 4, 5 -> "Weekday";
        //     case 6, 7 -> "Weekend";
        //     default -> "Invalid";
        // };
        // System.out.println("Expression: " + dayType2);
        
        // With yield (for multi-line cases)
        // String result = switch (day) {
        //     case 1, 2, 3, 4, 5 -> {
        //         System.out.println("It's a weekday");
        //         yield "Weekday";
        //     }
        //     case 6, 7 -> {
        //         System.out.println("It's weekend!");
        //         yield "Weekend";
        //     }
        //     default -> "Invalid";
        // };
        // System.out.println("Result: " + result);
        
        // Pattern matching in switch (Java 17+)
        // Object obj = "Hello";
        // String formatted = switch (obj) {
        //     case Integer i -> "Integer: " + i;
        //     case String s -> "String: " + s;
        //     case null -> "null value";
        //     default -> "Unknown type";
        // };
        // System.out.println(formatted);
    }
}
