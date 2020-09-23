package se.lexicon.michelle.booklender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.michelle.booklender.dto.BookDto;
import se.lexicon.michelle.booklender.service.BookService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class BookController {
    private static final String ALL = "all";
    public static final String TITLE = "title";
    public static final String AVAILABLE = "available";
    public static final String RESERVED = "reserved";

    private BookService bookService;

    /**
     * Constructor
     * @param bookService BookService
     */
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * find book By Id
     * @param bookId int
     * @return ResponseEntity
     */
    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> findById(@PathVariable int bookId){
        BookDto found = bookService.findById(bookId);
        if(found == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(found);
    }

    /**
     *
     * @param type String
     * @param value String
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<Object> find(
            @RequestParam(value = "type", defaultValue = ALL) String type,
            @RequestParam(value = "value", defaultValue = ALL) String value
            ){
        List<BookDto> found;
        switch (type.toLowerCase().trim()){
            case ALL:
                 found = bookService.findAll();

                if(found == null){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
                return ResponseEntity.ok(found);

            case TITLE:
                 found = bookService.findByTitle(value);
                if(found == null){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
                return ResponseEntity.ok(found);

            case AVAILABLE:
                found = bookService.findByAvailable(Boolean.parseBoolean(value));
                if(found == null){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
                return ResponseEntity.ok(found);

            case RESERVED:
                found = bookService.findByReserved(Boolean.parseBoolean(value));
                if(found == null){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
                return ResponseEntity.ok(found);

            default: throw new IllegalArgumentException("Not a valid type" + type);
        }

    }
    
    /**
     * create book
     * @param bookDto BookDto
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<BookDto> create(@Valid @RequestBody BookDto bookDto){
        if(bookDto.getBookId() != 0){
            throw  new IllegalArgumentException("ID is present, not allowed.");
        }
        BookDto createdBook = bookService.create(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    /**
     * update book
     * @param bookDto BookDto
     * @return ResponseEntity
     */
    @PutMapping//("/users")
    public ResponseEntity<BookDto> update(@RequestBody BookDto bookDto){

        if(bookDto.getBookId() == 0){
            throw new IllegalArgumentException("ID does not exist.");
        }

        return ResponseEntity.ok(bookService.update(bookDto));
    }


    /**
     * delete
     * @param bookId int
     * @return ResponseEntity
     */
    @DeleteMapping(value = "/{bookId}")
    public  ResponseEntity<BookDto> delete(@PathVariable int bookId){
        if (bookId == 0){
            throw new IllegalArgumentException("ID does not exist.");
        }
        BookDto found = bookService.findById(bookId);

        if(found == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        boolean isRemoved = bookService.delete(bookId);

        if(!isRemoved){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(found);
    }
}
