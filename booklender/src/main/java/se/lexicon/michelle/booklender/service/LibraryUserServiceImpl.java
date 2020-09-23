package se.lexicon.michelle.booklender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.michelle.booklender.dto.LibraryUserDto;
import se.lexicon.michelle.booklender.entity.LibraryUser;
import se.lexicon.michelle.booklender.exceptions.ResourceNotFoundException;
import se.lexicon.michelle.booklender.repository.LibraryUserRepository;


import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryUserServiceImpl implements LibraryUserService {

    LibraryUserRepository userRepository;

    /**
     * constructor
     * @param userRepository LibraryUserRepository
     */
    @Autowired
    public LibraryUserServiceImpl(LibraryUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * convert libraryUser To LibraryUserDto
     * @param libraryUser LibraryUser
     * @return LibraryUserDto
     */
    protected LibraryUserDto convertToLibraryUserDto(LibraryUser libraryUser){
        if(libraryUser == null){
            return null;
        }
        return new LibraryUserDto(
                libraryUser.getUserId(),
                libraryUser.getRegDate(),
                libraryUser.getName(),
                libraryUser.getEmail()
        );
    }

    /**
     * convert a list of LibraryUsers To List Of LibraryUserDtos
     * @param users List<LibraryUser>
     * @return List<LibraryUserDto>
     */
    protected List<LibraryUserDto> convertToListOfLibraryUserDtos(List<LibraryUser> users){
        if(users == null){
            return null;
        }
        List<LibraryUserDto> userDtos =  new ArrayList<>();

        for (LibraryUser user : users){
            LibraryUserDto userDto = convertToLibraryUserDto(user);
            userDtos.add(userDto);
        }

        return userDtos;
    }

    /**
     * find By Id
     * @param userId int
     * @return LibraryUserDto
     */
    @Override
    public LibraryUserDto findById(int userId) {
        LibraryUser user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Cannot find book with the id: " + userId));

        return convertToLibraryUserDto(user);
    }

    /**
     * find By Email
     * @param email String
     * @return LibraryUserDto
     */
    @Override
    public LibraryUserDto findByEmail(String email) {
        LibraryUser user = userRepository.findByEmailIgnoreCase(email);
        return convertToLibraryUserDto(user);
    }

    /**
     * find All
     * @return List<LibraryUserDto>
     */
    @Override
    public List<LibraryUserDto> findAll() {
        List<LibraryUser> foundItems = userRepository.findAll();

        return convertToListOfLibraryUserDtos(foundItems);
    }

    /**
     * create a user
     * @param userDto LibraryUserDto
     * @return LibraryUserDto
     */
    @Override
    @Transactional
    public LibraryUserDto create(LibraryUserDto userDto) {

        if(userDto == null)throw new ResourceNotFoundException("There is no LibraryUserDto object");

       LibraryUser user = new LibraryUser(
               userDto.getUserId(),
               userDto.getRegDate(),
               userDto.getName(),
               userDto.getEmail()
       );


        return convertToLibraryUserDto(userRepository.save(user));
    }

    /**
     * update a library user
     * @param userDto LibraryUserDto
     * @return LibraryUserDto
     */
    @Override
    @Transactional
    public LibraryUserDto update(LibraryUserDto userDto) {

        if(userDto == null)throw new ResourceNotFoundException("There is no LibraryUserDto object");

        LibraryUser user =  userRepository.findByUserId(userDto.getUserId());
        user.setRegDate(userDto.getRegDate());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        user = userRepository.save(user);

        return convertToLibraryUserDto(user);

    }

    /**
     * delete a user
     * @param userId int
     * @return boolean
     */
    @Override
    @Transactional
    public boolean delete(int userId) {



        if(userRepository.existsById(userId)){
            userRepository.delete(userRepository.findByUserId(userId) );
            return true;
        } else{
            throw new ResourceNotFoundException("There is no LibraryUserDto object");
        }

    }
}
