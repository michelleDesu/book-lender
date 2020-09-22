package se.lexicon.michelle.booklender.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.michelle.booklender.entity.LibraryUser;

import java.util.List;

public interface LibraryUserRepository extends CrudRepository<LibraryUser, Integer > {
    /**
     *
     * @return  List<LibraryUser>
     */
    List<LibraryUser> findAll();

    /**
     *
     * @param userId int
     * @return LibraryUser
     */
    LibraryUser findByUserId(int userId);

    /**
     *
     * @param email String
     * @return LibraryUser
     */
    LibraryUser findByEmailIgnoreCase(String email);
}
