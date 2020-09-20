package se.lexicon.michelle.booklender.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.michelle.booklender.entity.Book;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class BookRepositoryTest {

        @Autowired
        BookRepository bookRepository;

        Book firstBook;
        Book secondBook;

        @BeforeEach
        void setUp() {

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
                    false,
                    true,
                    10,
                    new BigDecimal("1.00"),
                    "This is another sample book"
            );

            bookRepository.save(firstBook);
            bookRepository.save(secondBook);

        }

        @Test
        void findAll() {
            List<Book> expected = new ArrayList<>();
            expected.add(firstBook);
            expected.add(secondBook);

            assertEquals(expected, bookRepository.findAll());

        }

        @Test
    void findByReserved(){

            Book expected = firstBook;

            assertEquals(expected, bookRepository.findByReserved(false));

        }

    @Test
    void findByAvailable(){

        Book expected = firstBook;

        assertEquals(expected, bookRepository.findByAvailable(true));

    }

    @Test
    void findByTitle(){

        Book expected = firstBook;

        assertEquals(expected, bookRepository.findByTitle("First book"));

    }


}

