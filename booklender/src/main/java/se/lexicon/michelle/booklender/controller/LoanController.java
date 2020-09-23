package se.lexicon.michelle.booklender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.michelle.booklender.dto.LoanDto;
import se.lexicon.michelle.booklender.service.LoanService;
import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping(path = "/loans")
public class LoanController {

    private static final String ALL = "all";
    public static final String BOOK_ID = "bookid";
    public static final String USER_ID = "userid";
    public static final String TERMINATED = "terminated";


    private LoanService loanService;

    /**
     * Constructor
     * @param loanService LoanService
     */
    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public ResponseEntity<Object> find(
            @RequestParam(value = "type", defaultValue = ALL) String type,
            @RequestParam(value = "value", defaultValue = ALL) String value
    ){
        List<LoanDto> found;

        switch (type.toLowerCase().trim()){

            case ALL:
                found = loanService.findAll();
                if(found == null){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
                return ResponseEntity.ok(found);

            case BOOK_ID:
                found = loanService.findByBookId(Integer.parseInt(value) );
                if(found == null){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
                return ResponseEntity.ok(found);

            case USER_ID:

                found = loanService.findByUserId(Integer.parseInt(value));
                if(found == null){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
                return ResponseEntity.ok(found);

            case TERMINATED:

                found = loanService.findByTerminated(Boolean.parseBoolean(value));
                if(found == null){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
                return ResponseEntity.ok(found);

            default: throw new IllegalArgumentException("Not a valid type: " + type);
        }
    }

    /**
     * find loan By Id
     * @param loanId long
     * @return ResponseEntity
     */
    @GetMapping("/{loanId}")
    public ResponseEntity<LoanDto> findById(@PathVariable long loanId){
        LoanDto found = loanService.findById(loanId);
        if(found == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(found);
    }


    /**
     * create loan
     * @param loanDto LoanDto
     * @return ResponseEntity
     */
     @PostMapping
    public ResponseEntity<LoanDto> create(@Valid @RequestBody LoanDto loanDto){
        if(loanDto.getLoanID() != 0){
            throw  new IllegalArgumentException("ID is present, not allowed.");
        }
        LoanDto createdBook = loanService.create(loanDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }


    /**
     * update an existing loan
     * @param loanDto LoanDto
     * @return ResponseEntity
     */
     @PutMapping//("/loans")
    public ResponseEntity<LoanDto> update(@RequestBody LoanDto loanDto){

        if(loanDto.getLoanID() == 0){
            throw new IllegalArgumentException("ID does not exist.");
        }
        LoanDto original = loanService.findById(loanDto.getLoanID());

         original.setLoanTaker(loanDto.getLoanTaker());
         original.setBook(loanDto.getBook());
         original.setLoanDate(loanDto.getLoanDate());
         original.setTerminated(loanDto.isTerminated());

        original =loanService.update(original);

        return ResponseEntity.ok(original);
    }



    /**
     * delete
     * @param loanId long
     * @return ResponseEntity
     */
    @DeleteMapping(value = "/{loanId}")
    public  ResponseEntity<LoanDto> delete(@PathVariable long loanId){
        if (loanId == 0){
            throw new IllegalArgumentException("ID does not exist.");
        }
        LoanDto found = loanService.findById(loanId);

        if(found == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        boolean isRemoved = loanService.delete(loanId);

        if(!isRemoved){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(found);
    }

}
