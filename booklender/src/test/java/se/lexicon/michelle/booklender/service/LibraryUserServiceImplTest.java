package se.lexicon.michelle.booklender.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.michelle.booklender.dto.LibraryUserDto;
import se.lexicon.michelle.booklender.entity.LibraryUser;
import se.lexicon.michelle.booklender.repository.LibraryUserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LibraryUserServiceImplTest {

    LibraryUserServiceImpl userService;
    LibraryUserDto firstUserDto;
    LibraryUserDto secondUserDto;


    @Autowired
    LibraryUserRepository userRepository;

    @BeforeEach
    void setUp() {
        LibraryUser firstUser;
        LibraryUser secondUser;

        userService = new LibraryUserServiceImpl(userRepository);

        firstUser = new LibraryUser(
                LocalDate.parse("2020-01-01"),
                "Test",
                "Testsson@test.com"
        );
        secondUser = new LibraryUser(
                LocalDate.parse("2020-01-01"),
                "Testan",
                "Testansson@test.com"
        );
        userRepository.save(firstUser);
        userRepository.save(secondUser);

        firstUserDto = userService.convertToLibraryUserDto(firstUser);
        secondUserDto = userService.convertToLibraryUserDto(secondUser);




    }

    @Test
    void findById() {
        LibraryUserDto expected = firstUserDto;
        assertEquals(expected, userService.findById(firstUserDto.getUserId()));
    }

    @Test
    void findByEmail() {
        LibraryUserDto expected = firstUserDto;
        assertEquals(expected, userService.findByEmail(firstUserDto.getEmail()));
    }

    @Test
    void findAll() {
        List<LibraryUserDto> expected = new ArrayList<>();
        expected.add(firstUserDto);
        expected.add(secondUserDto);

        assertEquals(expected, userService.findAll());
    }

    @Test
    void create() {
        LibraryUserDto expected = new LibraryUserDto(
                LocalDate.parse("2020-01-01"),
                "Saga",
                "Saga.estsson@test.com"
        );
        expected = userService.create(expected);

        assertEquals(expected, userService.findById(expected.getUserId()));

    }

    @Test
    void update() {
        LibraryUserDto expected = new LibraryUserDto(
                firstUserDto.getUserId(),
                LocalDate.parse("2020-01-02"),
                "Testaren",
                "Testsson@test.com"
        );

        firstUserDto.setRegDate( LocalDate.parse("2020-01-02"));
        firstUserDto.setName("Testaren");
        userService.update(firstUserDto);

        assertEquals(expected, firstUserDto);
    }

    @Test
    void delete() {
        List<LibraryUserDto> expected = new ArrayList<>();
        expected.add(firstUserDto);

        assertTrue(userService.delete(secondUserDto.getUserId()));
        assertFalse(userService.findAll().contains(secondUserDto));
        assertEquals(expected, userService.findAll());
    }
}