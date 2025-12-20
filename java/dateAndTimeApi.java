import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeExample {
    public static void main(String[] args) {
        // LocalDate - date without time
        LocalDate today = LocalDate.now();
        LocalDate specificDate = LocalDate.of(2025, 12, 25);
        LocalDate parsed = LocalDate.parse("2025-12-25");
        
        System.out.println("Today: " + today);
        System.out.println("Specific date: " + specificDate);
        
        // LocalTime - time without date
        LocalTime now = LocalTime.now();
        LocalTime specificTime = LocalTime.of(14, 30, 0);
        
        System.out.println("Now: " + now);
        System.out.println("Specific time: " + specificTime);
        
        // LocalDateTime - date and time
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime specificDateTime = LocalDateTime.of(2025, 12, 25, 14, 30);
        
        System.out.println("DateTime: " + dateTime);
        
        // ZonedDateTime - date-time with timezone
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZonedDateTime nyTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        
        System.out.println("Zoned: " + zonedDateTime);
        System.out.println("NY Time: " + nyTime);
        
        // Manipulating dates
        LocalDate tomorrow = today.plusDays(1);
        LocalDate nextWeek = today.plusWeeks(1);
        LocalDate nextMonth = today.plusMonths(1);
        LocalDate lastYear = today.minusYears(1);
        
        System.out.println("Tomorrow: " + tomorrow);
        System.out.println("Next week: " + nextWeek);
        
        // Period - date-based amount of time
        Period period = Period.between(today, specificDate);
        System.out.println("Days until Christmas: " + period.getDays());
        
        // Duration - time-based amount of time
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(17, 30);
        Duration duration = Duration.between(start, end);
        System.out.println("Work hours: " + duration.toHours());
        
        // Formatting
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formatted = dateTime.format(formatter);
        System.out.println("Formatted: " + formatted);
        
        // Parsing
        LocalDateTime parsedDateTime = LocalDateTime.parse("25/12/2025 14:30:00", formatter);
        System.out.println("Parsed: " + parsedDateTime);
        
        // Checking
        boolean isLeapYear = today.isLeapYear();
        boolean isBefore = today.isBefore(specificDate);
        boolean isAfter = today.isAfter(specificDate);
        
        System.out.println("Is leap year: " + isLeapYear);
        System.out.println("Is before Christmas: " + isBefore);
        
        // Getting components
        int year = today.getYear();
        Month month = today.getMonth();
        int dayOfMonth = today.getDayOfMonth();
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        
        System.out.println("Year: " + year);
        System.out.println("Month: " + month);
        System.out.println("Day: " + dayOfWeek);
        
        // Calculate age
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        int age = Period.between(birthDate, today).getYears();
        System.out.println("Age: " + age);
    }
}
