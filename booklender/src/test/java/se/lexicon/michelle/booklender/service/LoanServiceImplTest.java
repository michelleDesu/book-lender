package se.lexicon.michelle.booklender.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.michelle.booklender.dto.BookDto;
import se.lexicon.michelle.booklender.dto.LibraryUserDto;
import se.lexicon.michelle.booklender.dto.LoanDto;
import se.lexicon.michelle.booklender.entity.Book;
import se.lexicon.michelle.booklender.entity.LibraryUser;
import se.lexicon.michelle.booklender.entity.Loan;
import se.lexicon.michelle.booklender.repository.BookRepository;
import se.lexicon.michelle.booklender.repository.LibraryUserRepository;
import se.lexicon.michelle.booklender.repository.LoanRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LoanServiceImplTest {

    private LoanServiceImpl loanService;
    private BookServiceImpl bookService;
    private LibraryUserServiceImpl userService;


    @Autowired
    BookRepository bookRepository;

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    LibraryUserRepository userRepository;

    private LoanDto firstLoanDto;
    private LoanDto secondLoanDto;
    private BookDto firstBookDto;
    private BookDto secondBookDto;
    private LibraryUserDto firstUserDto;
    private LibraryUserDto secondUserDto;
    LibraryUser firstUser;
    LibraryUser secondUser;
    Book firstBook;
    Book secondBook;
    Loan firstLoan;
    Loan secondLoan;

    @BeforeEach
    void setUp() {


        loanService = new LoanServiceImpl(loanRepository, userRepository, bookRepository);
        bookService = new BookServiceImpl(bookRepository);
        userService = new LibraryUserServiceImpl(userRepository);




        firstBook = new Book(
                "Sample book",
                true,
                false,
                10,
                new BigDecimal("1.00"),
                "This is a sample book"
        );
        secondBook = new Book(
                "Another Sample Book",
                true,
                true,
                3,
                new BigDecimal("1.00"),
                "This is another sample book");

        firstUser = new LibraryUser(
                LocalDate.parse("2020-01-01"),
                "Test",
                "Testsson@test.com"
        );

        secondUser = new LibraryUser(
                LocalDate.parse("2020-01-01"),
                "Saga",
                "saga@test.com"
        );
        firstLoan = new Loan(
                firstUser,
                firstBook,
                LocalDate.now().minusDays(5),
                false);

        secondLoan = new Loan(
                secondUser,
                secondBook,
                LocalDate.now().minusDays(5),
                true);

        userRepository.save(firstUser);
        userRepository.save(secondUser);

        firstUserDto = userService.convertToLibraryUserDto(firstUser);
        secondUserDto = userService.convertToLibraryUserDto(secondUser);


        bookRepository.save(firstBook);
        bookRepository.save(secondBook);

        firstBookDto = bookService.convertToBookDto(firstBook);
        secondBookDto = bookService.convertToBookDto(secondBook);


        loanRepository.save(firstLoan);
        loanRepository.save(secondLoan);

        firstLoanDto = loanService.convertToLoanDto(firstLoan);
        secondLoanDto = loanService.convertToLoanDto(secondLoan);
    }


    @Test
    void findById() {
        LoanDto expected = firstLoanDto;
        assertEquals(expected, loanService.findById(firstLoanDto.getLoanID()));
    }

    @Test
    void findByBookId() {
        List<LoanDto> expected = new ArrayList<>();
        expected.add(firstLoanDto);
        assertEquals(expected, loanService.findByBookId(firstBookDto.getBookId()));
    }

    @Test
    void findByUserId() {
        List<LoanDto> expected = new ArrayList<>();
        expected.add(firstLoanDto);
        assertEquals(expected, loanService.findByUserId(firstLoanDto.getLoanTaker().getUserId()));

    }

    @Test
    void findByTerminated() {
        List<LoanDto> expected = new ArrayList<>();
        expected.add(secondLoanDto);
        assertEquals(expected, loanService.findByTerminated());

    }

    @Test
    void findAll() {
        List<LoanDto> expected = new ArrayList<>();
        expected.add(firstLoanDto);
        expected.add(secondLoanDto);
        assertEquals(expected, loanService.findAll());

    }

    @Test
    void create() {
        Book book = new Book(
                "Sagor från Hunaland book",
                true,
                true,
                10,
                new BigDecimal("1.00"),
                "Små berättelser från Hunaland."
        );
        LibraryUser user = new LibraryUser(
                LocalDate.parse("2020-01-01"),
                "Saga",
                "sagasemail@test.com"
        );

        LoanDto expected = new LoanDto(
                user,
                book,
                LocalDate.now().minusDays(5),
                false
        );
        expected = loanService.create(expected);
        assertEquals(expected, loanService.findById(expected.getLoanID()));


    }

    @Test
    void update() {
       LoanDto expected = new LoanDto(
               firstLoan.getLoanID(),
                firstUser,
                secondBook,
                LocalDate.now().minusDays(5),
                true);

       firstLoanDto.setBook(secondBook);
       firstLoanDto.setTerminated(true);
       loanService.update(firstLoanDto);

       assertEquals(expected, firstLoanDto);
    }

    @Test
    void delete() {
        List<LoanDto> expected = new ArrayList<>();
        expected.add(secondLoanDto);

        assertTrue(loanService.delete(firstLoanDto.getLoanID()));
        assertFalse(loanService.findAll().contains(firstLoanDto));
        assertEquals(expected, loanService.findAll());
    }
}