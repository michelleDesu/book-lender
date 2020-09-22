package se.lexicon.michelle.booklender.service;

import se.lexicon.michelle.booklender.dto.LibraryUserDto;

import java.util.List;

public interface LibraryUserService {
    LibraryUserDto findById(int userId);
    LibraryUserDto findByEmail(String email);
    List<LibraryUserDto> findAll();
    LibraryUserDto create(LibraryUserDto userDto);
    LibraryUserDto update(LibraryUserDto userDto);
    boolean delete(int userId);
}
