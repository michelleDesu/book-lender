package se.lexicon.michelle.booklender.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.michelle.booklender.entity.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {
    /**
     *
     * @return List<Book>
     */
    List<Book> findAll();

    /**
     *
     * @param BookId int
     * @return Book
     */
    Book findByBookId(int BookId);

    /**
     *
     * @param reserved boolean
     * @return List<Book>
     */
    List<Book> findByReserved(boolean reserved);

    /**
     *
     * @param available boolean
     * @return List<Book>
     */
    List<Book> findByAvailable(boolean available);

    /**
     *
     * @param Title String
     * @return List<Book>
     */
    List<Book> findByTitle(String Title);

}
