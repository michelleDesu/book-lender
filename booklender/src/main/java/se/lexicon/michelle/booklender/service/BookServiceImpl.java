package se.lexicon.michelle.booklender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.michelle.booklender.dto.BookDto;
import se.lexicon.michelle.booklender.entity.Book;
import se.lexicon.michelle.booklender.exceptions.ResourceNotFoundException;
import se.lexicon.michelle.booklender.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    BookRepository bookRepository;

    /**
     * Constructor
     * @param bookRepository BookRepository
     */
    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    /**
     * Converts from Book to BookDto
     * @param book Book
     * @return BookDto
     */
    protected BookDto convertToBookDto(Book book){

        if (book == null){
            return null;
        }
     return new BookDto(
                book.getBookId(),
                book.getTitle(),
                book.isAvailable(),
                book.isReserved(),
                book.getMaxLoanDays(),
                book.getFinePerDay(),
                book.getDescription()
        );
    }

    /**
     * Converts a list of Book to a list of BookDtos
     * @param books List<Book>
     * @return List<BookDto>
     */
    protected List<BookDto> convertToListOfBookDtos(List<Book> books){
        if (books == null){
            return null;
        }

        List<BookDto> bookDtoList = new ArrayList<>();

        for(Book book : books){
            BookDto toAdd = convertToBookDto(book);
            bookDtoList.add(toAdd);
        }
        return bookDtoList;
    }

    /**
     * find By Reserved
     * @param reserved boolean
     * @return List<BookDto>
     */
    @Override
    public List<BookDto> findByReserved(boolean reserved) {
        return convertToListOfBookDtos(bookRepository.findByReserved(reserved));
    }

    /**
     * find By Available
     * @param available boolean
     * @return List<BookDto>
     */
    @Override
    public List<BookDto> findByAvailable(boolean available) {
        return convertToListOfBookDtos(bookRepository.findByAvailable(available));
    }

    /**
     * find By Title
     * @param title String
     * @return List<BookDto>
     */
    @Override
    public List<BookDto> findByTitle(String title) {
        return convertToListOfBookDtos(bookRepository.findByTitle(title));
    }

    /**
     * find By Id
     * @param bookId int
     * @return BookDto
     */
    @Override
    public BookDto findById(int bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(()-> new ResourceNotFoundException("Cannot find book with the id: " + bookId));
        return convertToBookDto(book);
    }

    /**
     * find All
     * @return  List<BookDto>
     */
    @Override
    public List<BookDto> findAll() {
        List<Book> foundItems = bookRepository.findAll();
        return convertToListOfBookDtos(foundItems);
    }

    /**
     * create new Book
     * @param bookDto BookDto
     * @return BookDto
     */
    @Override
    @Transactional
    public BookDto create(BookDto bookDto) {

    if(bookDto == null)throw new ResourceNotFoundException("There is no bookDto object");
        if(bookDto.getBookId() != 0) throw new ResourceNotFoundException("Could NOT create a loan where the ID is specified");
    Book book = new Book(
            bookDto.getBookId(),
            bookDto.getTitle(),
            bookDto.isAvailable(),
            bookDto.isReserved(),
            bookDto.getMaxLoanDays(),
            bookDto.getFinePerDay(),
            bookDto.getDescription()
    );
        return convertToBookDto(bookRepository.save(book));
    }

    /**
     * update book
     * @param bookDto BookDto
     * @return BookDto
     */
    @Override
    @Transactional
    public BookDto update(BookDto bookDto) {

        if(bookDto == null)throw new ResourceNotFoundException("There is no bookDto object");
        Book book = bookRepository.findByBookId(bookDto.getBookId());

        book.setTitle(bookDto.getTitle());
        book.setAvailable(bookDto.isAvailable());
        book.setReserved(bookDto.isReserved());
        book.setMaxLoanDays(bookDto.getMaxLoanDays());
        book.setFinePerDay(bookDto.getFinePerDay());
        book.setDescription(bookDto.getDescription());

        book = bookRepository.save(book);
        return convertToBookDto(book);
    }

    /**
     * Delete a book
     * @param bookId int
     * @return boolean
     */
    @Override
    @Transactional
    public boolean delete(int bookId) {


        if(bookRepository.existsById(bookId)){
            bookRepository.delete(bookRepository.findByBookId(bookId));
            return true;
        }else{
            throw new ResourceNotFoundException("There is no bookDto object");
        }

    }
}
