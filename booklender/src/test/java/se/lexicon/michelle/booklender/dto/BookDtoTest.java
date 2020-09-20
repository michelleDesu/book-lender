package se.lexicon.michelle.booklender.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BookDtoTest {

    BookDto bookDto;
    @BeforeEach
    void setUp() {
        bookDto = new BookDto(
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
        assertEquals(expected, bookDto.getBookId());
    }

    @Test
    void getTitle() {
        String expected = "Sample book";

        assertEquals(expected, bookDto.getTitle());
    }

    @Test
    void setTitle() {
        String expected = "Same book";
        bookDto.setTitle(expected);

        assertEquals(expected, bookDto.getTitle());
    }

    @Test
    void isAvailable() {
        assertTrue(bookDto.isAvailable());
    }

    @Test
    void setAvailable() {
        bookDto.setAvailable(false);
        assertFalse(bookDto.isAvailable());
    }

    @Test
    void isReserved(){

        assertFalse(bookDto.isReserved());
    }

    @Test
    void setReserved() {
        bookDto.setReserved(true);
        assertTrue(bookDto.isReserved());
    }

    @Test
    void getMaxLoanDays() {
        int expected = 10;
        assertEquals(expected, bookDto.getMaxLoanDays());
    }

    @Test
    void setMaxLoanDays() {
        int expected = 5;
        bookDto.setMaxLoanDays(5);
        assertEquals(expected, bookDto.getMaxLoanDays());
    }

    @Test
    void getFinePerDay() {
        BigDecimal expected = new BigDecimal("1.00");

        assertEquals(expected, bookDto.getFinePerDay());
    }

    @Test
    void setFinePerDay() {
        BigDecimal expected = new BigDecimal("2.00");
        bookDto.setFinePerDay(new BigDecimal("2.00"));

        assertEquals(expected, bookDto.getFinePerDay());
    }

    @Test
    void getDescription() {
        String expected = "This is a sample book";
        assertEquals(expected, bookDto.getDescription());
    }

    @Test
    void setDescription() {
        String expected = "expected";
        bookDto.setDescription(expected);
        assertEquals(expected, bookDto.getDescription());
    }


    @Test
    public void testEquals_and_hashcode() {
        // equals and hashCode check name field value
        BookDto expected =  new BookDto(
                "Sample book",
                true,
                false,
                10,
                new BigDecimal("1.00"),
                "This is a sample book"

        );
        assertTrue(expected.equals(bookDto) && bookDto.equals(expected));
        assertEquals(expected.hashCode(), bookDto.hashCode());

    }

    @Test
    void testToString() {

        String expected = "BookDto{" + "bookId=" + bookDto.getBookId() +
                ", title='" + bookDto.getTitle() + '\'' +
                ", available=" + bookDto.isAvailable() +
                ", reserved=" + bookDto.isReserved() +
                ", maxLoanDays=" + bookDto.getMaxLoanDays() +
                ", finePerDay=" + bookDto.getFinePerDay() +
                ", description='" + bookDto.getDescription() + '\'' +
                '}';

        assertEquals(expected, bookDto.toString());
    }
}