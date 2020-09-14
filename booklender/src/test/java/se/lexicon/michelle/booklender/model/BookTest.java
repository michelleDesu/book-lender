package se.lexicon.michelle.booklender.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    private Book testObject;
    @BeforeEach
    void setUp() {
        testObject = new Book(
                "Sample book",
                true,
                false,
                10,
                new BigDecimal("1.00"),
                "This is a sample book"
        );
    }



    @Test
    void getBookId() {
        int expected = 0;
        assertEquals(expected, testObject.getBookId());
    }

    @Test
    void getTitle() {
        String expected = "Sample book";

        assertEquals(expected, testObject.getTitle());
    }

    @Test
    void setTitle() {
        String expected = "Same book";
        testObject.setTitle(expected);

        assertEquals(expected, testObject.getTitle());
    }

    @Test
    void isAvailable() {
        assertTrue(testObject.isAvailable());
    }

    @Test
    void setAvailable() {
        testObject.setAvailable(false);
        assertFalse(testObject.isAvailable());
    }

    @Test
    void isReserved(){

        assertFalse(testObject.isReserved());
    }

    @Test
    void setReserved() {
        testObject.setReserved(true);
        assertTrue(testObject.isReserved());
    }

    @Test
    void getMaxLoanDays() {
        int expected = 10;
        assertEquals(expected, testObject.getMaxLoanDays());
    }

    @Test
    void setMaxLoanDays() {
        int expected = 5;
        testObject.setMaxLoanDays(5);
        assertEquals(expected, testObject.getMaxLoanDays());
    }

    @Test
    void getFinePerDay() {
        BigDecimal expected = new BigDecimal("1.00");

        assertEquals(expected, testObject.getFinePerDay());
    }

    @Test
    void setFinePerDay() {
        BigDecimal expected = new BigDecimal("2.00");
        testObject.setFinePerDay(new BigDecimal("2.00"));

        assertEquals(expected, testObject.getFinePerDay());
    }

    @Test
    void getDescription() {
        String expected = "This is a sample book";
        assertEquals(expected, testObject.getDescription());
    }

    @Test
    void setDescription() {
        String expected = "expected";
        testObject.setDescription(expected);
        assertEquals(expected, testObject.getDescription());
    }

    @Test
    public void testEquals_and_hashcode() {
        // equals and hashCode check name field value
        Book expected =  new Book(
                "Sample book",
                true,
                false,
                10,
                new BigDecimal("1.00"),
                "This is a sample book"

        );
        assertTrue(expected.equals(testObject) && testObject.equals(expected));
        assertEquals(expected.hashCode(), testObject.hashCode());

    }

    @Test
    void testToString() {

        String expected = "Book{" + "bookId=" + testObject.getBookId() +
                ", title='" + testObject.getTitle() + '\'' +
                ", available=" + testObject.isAvailable() +
                ", reserved=" + testObject.isReserved() +
                ", maxLoanDays=" + testObject.getMaxLoanDays() +
                ", finePerDay=" + testObject.getFinePerDay() +
                ", description='" + testObject.getDescription() + '\'' +
                '}';

        assertEquals(expected, testObject.toString());
    }
}
