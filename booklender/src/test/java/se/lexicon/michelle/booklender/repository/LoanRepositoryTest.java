package se.lexicon.michelle.booklender.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.michelle.booklender.entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LoanRepositoryTest {

    @Autowired
    LoanRepository loanRepository;
    LibraryUser firstUser;
    Book firstBook;
    Book secondBook;

    Loan firstLoan;
    Loan secondLoan;


    @BeforeEach
    void setUp() {

        firstUser = new LibraryUser(
                LocalDate.parse("2020-01-01"),
                "First Test",
                "firstTestsson@test.com"
        );

        firstBook = new Book(
                "First book",
                true,
                false,
                10,
                new BigDecimal("1.00"),
                "This is a sample book"
        );

        secondBook = new Book(
                "Second book",
                true,
                false,
                10,
                new BigDecimal("1.00"),
                "This is another sample book"
        );

        firstLoan = new Loan(
                firstUser,
                firstBook,
                LocalDate.now().minusDays(5),
                false);
        secondLoan = new Loan(
                firstUser,
                secondBook,
                LocalDate.now().minusDays(5),
                true);


        loanRepository.save(firstLoan);
        loanRepository.save(secondLoan);
    }

    @Test
    void findAll() {

        List<Loan> expected = new ArrayList<>();
        expected.add(firstLoan);
        expected.add(secondLoan);

        assertEquals(expected, loanRepository.findAll());

    }

    @Test
    void findByUserId(){
        List<Loan> expected = new ArrayList<>();
        expected.add(firstLoan);
        expected.add(secondLoan);

        assertEquals(expected, loanRepository.findAllByLoanTaker_userId(firstLoan.getLoanTaker().getUserId()));
    }

    @Test
    void findAllByBook_bookId(){
        List<Loan> expected = new ArrayList<>();
        expected.add(firstLoan);
        assertEquals(expected, loanRepository.findAllByBook_bookId(firstLoan.getBook().getBookId()));
    }

    @Test
    void findAllByIsTerminated(){
        List<Loan> expected = new ArrayList<>();
        expected.add(secondLoan);

        assertEquals(expected, loanRepository.findAllByTerminated(true));
    }


}