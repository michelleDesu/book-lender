package se.lexicon.michelle.booklender.service;

import se.lexicon.michelle.booklender.dto.BookDto;

import java.util.List;

public interface BookService {

    /**
     *
     * @param reserved boolean
     * @return List<BookDto>
     */
    List<BookDto> findByReserved(boolean reserved);

    /**
     *
     * @param available boolean
     * @return List<BookDto>
     */
    List<BookDto> findByAvailable(boolean available);

    /**
     *
     * @param title String
     * @return List<BookDto>
     */
    List<BookDto> findByTitle(String title);

    /**
     *
     * @param bookId int
     * @return BookDto
     */
    BookDto findById(int bookId);

    /**
     *
     * @return List<BookDto>
     */
    List<BookDto> findAll();

    /**
     * Create a book
     * @param bookDto BookDto
     * @return BookDto
     */
    BookDto create(BookDto bookDto);

    /**
     * Update a Book
     * @param bookDto BookDto
     * @return BookDto
     */
    BookDto update(BookDto bookDto);

    /**
     * delete a Book
     * @param bookId int
     * @return boolean
     */
    boolean delete(int bookId);

}
