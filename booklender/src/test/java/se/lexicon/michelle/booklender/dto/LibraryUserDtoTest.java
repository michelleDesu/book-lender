package se.lexicon.michelle.booklender.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LibraryUserDtoTest {

    LibraryUserDto libraryUserDto;

    @BeforeEach
    void setUp() {


        libraryUserDto = new LibraryUserDto(
                LocalDate.parse("2020-01-01"),
                "Test",
                "Testsson@test.com"
        );

    }

    @Test
    void getUserId() {
        int expected = 0;
        assertEquals(expected, libraryUserDto.getUserId());
    }

    @Test
    void getRegDate() {
        LocalDate expected = LocalDate.parse("2020-01-01");
        assertEquals(expected, libraryUserDto.getRegDate());
    }

    @Test
    void setRegDate() {
        LocalDate expected = LocalDate.parse("2020-01-02");
        libraryUserDto.setRegDate(expected);
        assertEquals(expected, libraryUserDto.getRegDate());
    }

    @Test
    void getName() {
        String expected = "Test";
        assertEquals(expected, libraryUserDto.getName() );
    }

    @Test
    void setName() {
        String expected = "Testan";
        libraryUserDto.setName(expected);
        assertEquals(expected, libraryUserDto.getName() );
    }

    @Test
    void getEmail() {
        String expected = "Testsson@test.com";
        assertEquals(expected, libraryUserDto.getEmail() ) ;
    }

    @Test
    void setEmail() {
        String expected = "Testan@test.com";
        libraryUserDto.setEmail(expected);
        assertEquals(expected, libraryUserDto.getEmail() ) ;
    }

    @Test
    void testToString() {

        String expected = "LibraryUserDto{" + "userId=" + libraryUserDto.getUserId() +
                ", regDate=" + libraryUserDto.getRegDate() +
                ", name='" + libraryUserDto.getName() + '\'' +
                ", email='" + libraryUserDto.getEmail() + '\'' +
                '}';

        assertEquals(expected, libraryUserDto.toString());

    }

    @Test
    public void testEquals_and_hashcode() {
        LibraryUserDto  expected = new LibraryUserDto(
                LocalDate.parse("2020-01-01"),
                "Test",
                "Testsson@test.com"
        );

        assertTrue(expected.equals(libraryUserDto) && libraryUserDto.equals(expected));
        assertEquals(expected.hashCode(), libraryUserDto.hashCode());
    }

}