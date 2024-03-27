import org.example.DateUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DateUtilsTest {
    private DateUtils dateUtils;

    @BeforeEach
    public void initMethod() {
        dateUtils = new DateUtils();
    }
    @AfterEach
    public void clearMethod() {
        dateUtils = null;
    }

    @Test
    public void testIsLeapYear() {
        Assertions.assertTrue(dateUtils.isLeapYear(2024));
        Assertions.assertFalse(dateUtils.isLeapYear(2025));
        Assertions.assertTrue(dateUtils.isLeapYear(1504));
        Assertions.assertFalse(dateUtils.isLeapYear(1500));
        Assertions.assertTrue(dateUtils.isLeapYear(1600));
    }
    @Test
    public void testGetDaysInMonth() {
        Assertions.assertEquals(31, dateUtils.getDaysInMonth(2024, 1));
        Assertions.assertEquals(29, dateUtils.getDaysInMonth(2024, 2));
        Assertions.assertEquals(28, dateUtils.getDaysInMonth(2025, 2));
        Assertions.assertEquals(30, dateUtils.getDaysInMonth(2024, 4));
    }
    @Test
    public void testGetDaysInMonthInvalid() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class,
                                ()-> dateUtils.getDaysInMonth(2024, -1));
        Assertions.assertEquals("Invalid month value: -1", exception.getMessage());
    }
}
