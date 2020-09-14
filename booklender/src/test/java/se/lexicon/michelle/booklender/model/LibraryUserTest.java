package se.lexicon.michelle.booklender.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LibraryUserTest {

    LibraryUser testObject;

    @BeforeEach
    void setUp() {

        testObject =  new LibraryUser(
                LocalDate.parse("2020-01-01"),
                "Test",
                "Testsson@test.com"
        );
    }

    @Test
    void getUserId() {
        int expected = 0;
        assertEquals(expected, testObject.getUserId());
    }

    @Test
    void getRegDate() {
        LocalDate expected = LocalDate.parse("2020-01-01");
        assertEquals(expected, testObject.getRegDate());
    }

    @Test
    void getName() {
        String expected = "Test";
        assertEquals(expected, testObject.getName() );

    }

    @Test
    void setName() {
        String expected = "Testan";
        testObject.setName(expected);
        assertEquals(expected, testObject.getName() );
    }

    @Test
    void getEmail() {
        String expected = "Testsson@test.com";
        assertEquals(expected, testObject.getEmail() ) ;
    }

    @Test
    void setEmail() {
        String expected = "Testan@test.com";
        testObject.setEmail(expected);
        assertEquals(expected, testObject.getEmail() ) ;
    }

    @Test
    public void testEquals_and_hashcode() {
        LibraryUser expected =  testObject =  new LibraryUser(
                LocalDate.parse("2020-01-01"),
                "Test",
                "Testsson@test.com"
        );

        assertTrue(expected.equals(testObject) && testObject.equals(expected));
        assertEquals(expected.hashCode(), testObject.hashCode());
    }

    @Test
    void testToString() {
        String expected = "LibraryUser{" + "userId=" + testObject.getUserId() +
                ", regDate=" + testObject.getRegDate() +
                ", name='" + testObject.getName() + '\'' +
                ", email='" + testObject.getEmail() + '\'' +
                '}';

        assertEquals(expected, testObject.toString());

    }
}