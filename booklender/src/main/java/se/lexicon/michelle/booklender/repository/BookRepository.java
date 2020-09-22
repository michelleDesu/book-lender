package se.lexicon.michelle.booklender.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.michelle.booklender.entity.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findAll();

    Book findByBookId(int BookId);

    List<Book> findByReserved(boolean reserved);

    List<Book> findByAvailable(boolean available);

    List<Book> findByTitle(String Title);

}
