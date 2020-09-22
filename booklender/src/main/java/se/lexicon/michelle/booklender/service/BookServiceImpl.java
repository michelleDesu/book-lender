package se.lexicon.michelle.booklender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.michelle.booklender.dto.BookDto;
import se.lexicon.michelle.booklender.entity.Book;
import se.lexicon.michelle.booklender.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    protected BookDto convertToBookDto(Book book){
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
    protected List<BookDto> convertToListOfBookDtos(List<Book> books){
        List<BookDto> bookDtoList = new ArrayList<>();

        for(Book book : books){
            BookDto toAdd = convertToBookDto(book);
            bookDtoList.add(toAdd);
        }
        return bookDtoList;
    }

    @Override
    public List<BookDto> findByReserved(boolean reserved) {
        return convertToListOfBookDtos(bookRepository.findByReserved(reserved));
    }

    @Override
    public List<BookDto> findByAvailable(boolean available) {
        return convertToListOfBookDtos(bookRepository.findByAvailable(available));
    }

    @Override
    public List<BookDto> findByTitle(String title) {
        return convertToListOfBookDtos(bookRepository.findByTitle(title));
    }

    @Override
    public BookDto findById(int bookId) {
        Book book = bookRepository.findByBookId(bookId);
        return convertToBookDto(book);
    }

    @Override
    public List<BookDto> findAll() {
        List<Book> foundItems = bookRepository.findAll();
        return convertToListOfBookDtos(foundItems);
    }

    @Override
    public BookDto create(BookDto bookDto) {
        //TODO insert exception here
    if(bookDto == null){
        return null;
    }
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

    @Override
    public BookDto update(BookDto bookDto) {
        //TODO insert exception here
        if(bookDto == null){
            return null;
        }
        Book book = bookRepository.findByBookId(bookDto.getBookId());

        book.setTitle(bookDto.getTitle());
        book.setAvailable(bookDto.isAvailable());
        book.setReserved(bookDto.isReserved());
        bookDto.setMaxLoanDays(bookDto.getMaxLoanDays());
        bookDto.setFinePerDay(bookDto.getFinePerDay());
        bookDto.setDescription(bookDto.getDescription());

        book = bookRepository.save(book);
        return convertToBookDto(book);
    }

    @Override
    public boolean delete(int bookId) {

        //TODO insert exception here
        boolean isDeleted = false;
        if(bookRepository.existsById(bookId)){
            bookRepository.delete(bookRepository.findByBookId(bookId));
            isDeleted = true;
        }
        return isDeleted;
    }
}
