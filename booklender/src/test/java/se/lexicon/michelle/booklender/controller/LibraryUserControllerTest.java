package se.lexicon.michelle.booklender.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LibraryUserControllerTest {

    @Autowired
    private LibraryUserController userController;

    @BeforeEach
    void setUp() {
    }

    @Test
    void contextLoads(){
        assertNotNull(userController);
    }
    @Test
    void findById() {
    }

    @Test
    void findByEmail() {
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}