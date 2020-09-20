package se.lexicon.michelle.booklender.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.michelle.booklender.entity.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findAll();

    Book findByReserved(boolean reserved);

    Book findByAvailable(boolean available);

    Book findByTitle(String Title);

}
