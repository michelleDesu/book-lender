package se.lexicon.michelle.booklender.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.michelle.booklender.dto.BookDto;
import se.lexicon.michelle.booklender.entity.Book;
import se.lexicon.michelle.booklender.repository.BookRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookServiceImplTest {

    BookServiceImpl bookService;
    BookDto firstBookDto;
    BookDto secondBookDto;

    @Autowired
    BookRepository bookRepository;

    @BeforeEach
    void setUp() {

        bookService = new BookServiceImpl(bookRepository);

     Book  firstBook = new Book(
                "Sample book",
                true,
                false,
                10,
                new BigDecimal("1.00"),
                "This is a sample book"
        );

        Book secondBook = new Book(
                "Another Sample book",
                false,
                true,
                10,
                new BigDecimal("1.00"),
                "This is another sample book"
        );

        bookRepository.save(firstBook);
        bookRepository.save(secondBook);

        firstBookDto = bookService.convertToBookDto(firstBook);
        secondBookDto = bookService.convertToBookDto(secondBook);

    }



    @Test
    void findByReserved() {
        List<BookDto> expected = new ArrayList<>();
        expected.add(secondBookDto);

        assertEquals(expected, bookService.findByReserved(true));
    }

    @Test
    void findByAvailable() {
        List<BookDto> expected = new ArrayList<>();
        expected.add(firstBookDto);

        assertEquals(expected, bookService.findByAvailable(true));
    }

    @Test
    void findByTitle() {
        List<BookDto> expected = new ArrayList<>();
        expected.add(firstBookDto);

        assertEquals(expected, bookService.findByTitle(firstBookDto.getTitle()));
    }

    @Test
    void findById() {

        BookDto expected = firstBookDto;
        assertEquals(expected, bookService.findById(firstBookDto.getBookId()));
    }

    @Test
    void findAll() {
        List<BookDto> expected = new ArrayList<>();
        expected.add(firstBookDto);
        expected.add(secondBookDto);
        assertEquals(expected, bookService.findAll());
    }

    @Test
    void create() {
        BookDto expected = new BookDto(
                "Sagas book",
                true,
                false,
                10,
                new BigDecimal("1.00"),
                "This is a sample book"
        );
        expected = bookService.create(expected);
    }

    @Test
    void update() {
        BookDto expected = new BookDto(
                firstBookDto.getBookId(),
                "Samples book",
                true,
                false,
                10,
                new BigDecimal("1.00"),
                "This was a sample book"
        );
        firstBookDto.setDescription(expected.getDescription());
        firstBookDto.setTitle(expected.getTitle());

        bookService.update(firstBookDto);

        assertEquals(expected, firstBookDto);

    }

    @Test
    void delete() {
        List<BookDto> expected = new ArrayList<>();
        expected.add(secondBookDto);

        assertTrue(bookService.delete(firstBookDto.getBookId()));
        assertFalse(bookService.findAll().contains(firstBookDto));
        assertEquals(expected, bookService.findAll());
    }
}