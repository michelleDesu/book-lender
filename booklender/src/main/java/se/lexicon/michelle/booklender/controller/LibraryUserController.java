package se.lexicon.michelle.booklender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.michelle.booklender.dto.LibraryUserDto;
import se.lexicon.michelle.booklender.entity.LibraryUser;
import se.lexicon.michelle.booklender.service.LibraryUserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class LibraryUserController {

    private LibraryUserService userService;

    /**
     * Constructor
     * @param userService LibraryUserService
     */
    @Autowired
    public LibraryUserController(LibraryUserService userService) {
        this.userService = userService;
    }

    /**
     * findById
     * @param userId int
     * @return ResponseEntity<LibraryUserDto>
     */
    @GetMapping("/{userId}")
    public ResponseEntity<LibraryUserDto> findById(@PathVariable int userId){
        LibraryUserDto found = userService.findById(userId);

        if (found == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(found);
    }

    /**
     * findByEmail
     * @param email String
     * @return ResponseEntity<LibraryUserDto>
     */
    //Todo check the path
    @GetMapping("users/email/{email}")
    public ResponseEntity<LibraryUserDto> findByEmail(@Valid @PathVariable String email){

        LibraryUserDto found = userService.findByEmail(email);
        if(found == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(found);
    }

    /**
     * findAll
     * @return ResponseEntity<LibraryUserDto>
     */
    @GetMapping
    public ResponseEntity< List<LibraryUserDto>> findAll(){
        List<LibraryUserDto> found = userService.findAll();
        if(found == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(found);
    }

    /**
     * create user
     * @param userDto LibraryUserDto
     * @return ResponseEntity<LibraryUserDto>
     */
    @PostMapping//("/users")
    public ResponseEntity<LibraryUserDto> create(@RequestBody LibraryUserDto userDto){
        if(userDto.getUserId() != 0 ){
            throw  new IllegalArgumentException("ID is present, not allowed.");
        }

        LibraryUserDto createdUserDto = userService.create(userDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
    }

    /**
     * update user
     * @param userDto LibraryUserDto
     * @return ResponseEntity<LibraryUserDto>
     */
    @PutMapping("/users")
    public ResponseEntity<LibraryUserDto> update(@Valid @RequestBody LibraryUserDto userDto){
        if(userDto.getUserId() == 0){
            throw new IllegalArgumentException("ID does not exist.");
        }
        LibraryUserDto original = userService.findById(userDto.getUserId()); //.orElseThrow(()-> new ResourceNotFoundException("Could not find user with ID: " + userId));
        original.setRegDate(userDto.getRegDate());
        original.setName(userDto.getName());
        original.setEmail(userDto.getEmail() );

        original = userService.update(original);

        return ResponseEntity.ok(original);
    }

    /**
     * delete user
     * @param userId int
     * @return ResponseEntity<LibraryUserDto>
     */
    @DeleteMapping(value = "/{userId}")
    public  ResponseEntity<LibraryUserDto> delete(@PathVariable int userId){
        if(userId == 0){
            throw new IllegalArgumentException("ID does not exist.");
        }
        LibraryUserDto found = userService.findById(userId);

        if (found == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        boolean isRemoved = userService.delete(userId);
        if(!isRemoved){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(found);
    }



}
