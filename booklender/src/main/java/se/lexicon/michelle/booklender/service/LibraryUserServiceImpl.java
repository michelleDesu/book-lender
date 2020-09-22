package se.lexicon.michelle.booklender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.michelle.booklender.dto.LibraryUserDto;
import se.lexicon.michelle.booklender.entity.LibraryUser;
import se.lexicon.michelle.booklender.repository.LibraryUserRepository;


import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryUserServiceImpl implements LibraryUserService {

    LibraryUserRepository userRepository;

    @Autowired
    public LibraryUserServiceImpl(LibraryUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    protected LibraryUserDto convertToLibraryUserDto(LibraryUser libraryUser){
        return new LibraryUserDto(
                libraryUser.getUserId(),
                libraryUser.getRegDate(),
                libraryUser.getName(),
                libraryUser.getEmail()
        );
    }

    protected List<LibraryUserDto> convertToListOfLibraryUserDtos(List<LibraryUser> users){
        List<LibraryUserDto> userDtos =  new ArrayList<>();

        for (LibraryUser user : users){
            LibraryUserDto userDto = convertToLibraryUserDto(user);
            userDtos.add(userDto);
        }

        return userDtos;
    }

    @Override
    public LibraryUserDto findById(int userId) {
        LibraryUser user = userRepository.findByUserId(userId);
        return convertToLibraryUserDto(user);
    }

    @Override
    public LibraryUserDto findByEmail(String email) {
        LibraryUser user = userRepository.findByEmailIgnoreCase(email);
        return convertToLibraryUserDto(user);
    }

    @Override
    public List<LibraryUserDto> findAll() {
        List<LibraryUser> foundItems = userRepository.findAll();

        return convertToListOfLibraryUserDtos(foundItems);
    }

    @Override
    @Transactional
    public LibraryUserDto create(LibraryUserDto userDto) {

        //TODO insert exception here
        if(userDto == null){
            return null;
        }
       LibraryUser user = new LibraryUser(
               userDto.getUserId(),
               userDto.getRegDate(),
               userDto.getName(),
               userDto.getEmail()
       );


        return convertToLibraryUserDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public LibraryUserDto update(LibraryUserDto userDto) {

        //Todo insert thrown exception here
        if(userDto == null){
            return null;
        }

        LibraryUser user =  userRepository.findByUserId(userDto.getUserId());
        user.setRegDate(userDto.getRegDate());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        user = userRepository.save(user);


        //Todo return userDto instead?
        return convertToLibraryUserDto(user);

    }

    @Override
    @Transactional
    public boolean delete(int userId) {

        //Todo add throw exception here
        boolean deleted = false;
        if(userRepository.existsById(userId)){
            userRepository.delete(userRepository.findByUserId(userId) );
            deleted = true;
        }

        return deleted;
    }
}
