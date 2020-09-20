package se.lexicon.michelle.booklender.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.michelle.booklender.entity.LibraryUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LibraryUserRepositoryTest {

    @Autowired
    LibraryUserRepository libraryUserRepository;

    LibraryUser firstUser;
    LibraryUser secondUser;

    @BeforeEach
    void setUp() {

        firstUser = new LibraryUser(
                LocalDate.parse("2020-01-01"),
                "First Test",
                "firstTestsson@test.com"
        );

        secondUser = new LibraryUser(
                LocalDate.parse("2020-01-01"),
                "Second Test",
                "secondTestsson@test.com"
        );

        libraryUserRepository.save(firstUser);
        libraryUserRepository.save(secondUser);

    }

    @Test
    void findAll() {

        List<LibraryUser> expected = new ArrayList<>();
        expected.add(firstUser);
        expected.add(secondUser);

        assertEquals(expected, libraryUserRepository.findAll());
    }

    @Test
    void findByEmailIgnoreCase(){
        LibraryUser expected = firstUser;

        assertEquals(expected, libraryUserRepository.findByEmailIgnoreCase("firsttestsson@test.com"));
    }


}