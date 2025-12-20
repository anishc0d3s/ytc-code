public class EnhancedNPE {
    static class Person {
        String name;
        Address address;
    }
    
    static class Address {
        String city;
        ZipCode zipCode;
    }
    
    static class ZipCode {
        String code;
    }
    
    public static void main(String[] args) {
        Person person = new Person();
        person.name = "John";
        person.address = new Address();
        person.address.city = "New York";
        // person.address.zipCode is null
        
        try {
            // In Java 14+, NPE message shows exactly which part is null
            String zip = person.address.zipCode.code.toUpperCase();
        } catch (NullPointerException e) {
            // Old message: "Cannot invoke String.toUpperCase() because..."
            // New message shows: "Cannot invoke 'ZipCode.code' because 'person.address.zipCode' is null"
            System.out.println("Enhanced NPE message:");
            System.out.println(e.getMessage());
        }
    }
}
