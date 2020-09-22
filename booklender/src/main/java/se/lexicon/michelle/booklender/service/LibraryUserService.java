package se.lexicon.michelle.booklender.service;

import se.lexicon.michelle.booklender.dto.LibraryUserDto;

import java.util.List;

public interface LibraryUserService {

    /**
     * find By Id
     * @param userId int
     * @return LibraryUserDto
     */
    LibraryUserDto findById(int userId);

    /**
     *find By Email
     * @param email String
     * @return LibraryUserDto
     */
    LibraryUserDto findByEmail(String email);


    /**
     * find All
     * @return  List<LibraryUserDto>
     */
    List<LibraryUserDto> findAll();

    /**
     * create a library user
     * @param userDto LibraryUserDto
     * @return LibraryUserDto
     */
    LibraryUserDto create(LibraryUserDto userDto);

    /**
     * Updates a Library User
     * @param userDto LibraryUserDto
     * @return LibraryUserDto
     */
    LibraryUserDto update(LibraryUserDto userDto);

    /**
     *
     * @param userId int
     * @return boolean
     */
    boolean delete(int userId);
}
