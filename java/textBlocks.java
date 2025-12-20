public class TextBlocksExample {
    public static void main(String[] args) {
        // Traditional multi-line string
        String html1 = "<html>\n" +
                      "    <body>\n" +
                      "        <h1>Hello World</h1>\n" +
                      "    </body>\n" +
                      "</html>";
        
        // Text block (Java 13+)
        String html2 = """
                <html>
                    <body>
                        <h1>Hello World</h1>
                    </body>
                </html>
                """;
        
        System.out.println(html2);
        
        // JSON example
        String json = """
                {
                    "name": "John Doe",
                    "age": 30,
                    "email": "john@example.com",
                    "address": {
                        "street": "123 Main St",
                        "city": "New York"
                    }
                }
                """;
        System.out.println(json);
        
        // SQL query
        String query = """
                SELECT users.name, orders.total
                FROM users
                JOIN orders ON users.id = orders.user_id
                WHERE orders.total > 100
                ORDER BY orders.total DESC
                """;
        System.out.println(query);
        
        // Escaping and formatting
        String name = "Alice";
        int age = 25;
        String message = """
                Hello, %s!
                You are %d years old.
                """.formatted(name, age);
        System.out.println(message);
        
        // Preserving trailing spaces with \s
        String withSpaces = """
                Line 1    \s
                Line 2\s
                """;
        
        // Line continuation with \
        String singleLine = """
                This is a very long line \
                that continues on the next line \
                without line breaks.
                """;
        System.out.println(singleLine);
    }
}
