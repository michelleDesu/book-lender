package se.lexicon.michelle.booklender.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.michelle.booklender.entity.Book;
import se.lexicon.michelle.booklender.entity.LibraryUser;


import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LoanDtoDtoTest {

    private LibraryUser loanTaker;
    private Book book;
    private Book loanedBook;

    private LoanDto testObject;
    private LoanDto testLoanDto;


    @BeforeEach
    void setUp() {
        loanTaker = new LibraryUser(
                LocalDate.parse("2020-01-01"),
                "Test",
                "Testsson@test.com"
        );
        book = new Book(
                "Sample book",
                true,
                false,
                10,
                new BigDecimal("1.00"),
                "This is a sample book"
        );
        loanedBook = new Book(
                "Sample",
                true,
                true,
                3,
                new BigDecimal("1.00"),
                "This is a sample book");

        testLoanDto = new LoanDto(
                loanTaker,
                loanedBook,
                LocalDate.now().minusDays(5),
                false);

        testObject = new LoanDto(
                loanTaker,
                book,
                LocalDate.now().minusDays(5),
                true
        );

    }

    @Test
    void getLoanTaker() {

        LibraryUser expected = loanTaker;
        assertEquals(expected, testObject.getLoanTaker());
    }

    @Test
    void setLoanTaker() {
        LibraryUser expected = new LibraryUser(
                LocalDate.parse("2020-01-01"),
                "Testare",
                "Testsson@test.com"
        );
        testObject.setLoanTaker( new LibraryUser(
                LocalDate.parse("2020-01-01"),
                "Testare",
                "Testsson@test.com"
        ));
        assertEquals(expected, testObject.getLoanTaker());
    }

    @Test
    void getAndSetBook() {
        Book expected = book;

        assertEquals(expected, testObject.getBook());

    }

    @Test
    void setLoanDate(){
        LocalDate expectedLoanDate =   LocalDate.now();
        testLoanDto.setLoanDate(expectedLoanDate);
        assertEquals(expectedLoanDate, testLoanDto.getLoanDate());
    }

    @Test
    void setBook() {
        Book expected = new Book(
                "Sample",
                true,
                true,
                10,
                new BigDecimal("1.00"),
                "This is a sample book");

        testObject.setBook(expected);
        assertEquals(expected, testObject.getBook());

    }

    @Test
    void getLoanDate() {
        LocalDate expected = LocalDate.now().minusDays(5);

        assertEquals(expected, testObject.getLoanDate());
    }

    @Test
    void isTerminated() {
        assertTrue(testObject.isTerminated());
        assertFalse(testLoanDto.isTerminated());

    }

    @Test
    void setTerminated(){
        testObject.setTerminated(false);
        assertFalse(testObject.isTerminated());
    }

    @Test
    public void testEquals_and_hashcode() {
        // equals and hashCode check name field value
        LoanDto expected =  new LoanDto(
                loanTaker,
                book,
                LocalDate.now().minusDays(5),
                true

        );
        assertTrue(expected.equals(testObject) && testObject.equals(expected));
        assertEquals(expected.hashCode(), testObject.hashCode());

    }

    @Test
    void testToString() {
        String expected =  "LoanDto{" + "loanID=" + testObject.getLoanID() +
                ", loanTaker=" + testObject.getBook() +
                ", book=" + testObject.getBook() +
                ", loanDate=" + testObject.getLoanDate() +
                ", terminated=" + testObject.isTerminated() +
                '}';
    }
}