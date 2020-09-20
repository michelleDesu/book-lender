package se.lexicon.michelle.booklender.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {

    private LibraryUser loanTaker;
    private Book book;
    private Book loanedBook;

    private Loan testObject;
    private  Loan testLoan;


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

         testLoan = new Loan(
                loanTaker,
                loanedBook,
                LocalDate.now().minusDays(5),
                false);

    testObject = new Loan(
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
    void isOverdue() {

        assertFalse(testObject.isOverdue());
        assertTrue(testLoan.isOverdue());
    }

    @Test
    void getFine() {

        BigDecimal expectedFine =  BigDecimal.valueOf(2.0).setScale(2, RoundingMode.HALF_UP);
        assertEquals(expectedFine, testLoan.getFine());
    }

    @Test
    void getLoanDate() {
        LocalDate expected = LocalDate.now().minusDays(5);

        assertEquals(expected, testObject.getLoanDate());
    }

    @Test
    void isExpired() {
        assertTrue(testObject.isExpired());
        assertFalse(testLoan.isExpired());

    }

    @Test
    void setExpired(){
        testObject.setExpired(false);
        assertFalse(testObject.isExpired());
    }



    @Test
    void extendLoan() {
        LocalDate expectedDate = LocalDate.now().plusDays(5);

        assertFalse(testObject.extendLoan(10));
        assertTrue(testObject.extendLoan(5));
        assertEquals(expectedDate, testObject.getLoanDate());
    }


    @Test
    void testToString() {
        String expected = "Loan{" + "loanID=" + testObject.getLoanID() +
                ", loanTaker=" + loanTaker +
                ", book=" + book +
                ", loanDate=" + testObject.getLoanDate() +
                ", terminated=" + testObject.isExpired() +
                '}';

        assertEquals(expected, testObject.toString());
    }

    @Test
    public void testEquals_and_hashcode() {
        // equals and hashCode check name field value
        Loan expected =  new Loan(
                loanTaker,
                book,
                LocalDate.now().minusDays(5),
                true

        );
        assertTrue(expected.equals(testObject) && testObject.equals(expected));
        assertEquals(expected.hashCode(), testObject.hashCode());

    }
}