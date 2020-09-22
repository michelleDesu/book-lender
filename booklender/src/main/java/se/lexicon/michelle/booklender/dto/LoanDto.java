package se.lexicon.michelle.booklender.dto;

import se.lexicon.michelle.booklender.entity.Book;
import se.lexicon.michelle.booklender.entity.LibraryUser;

import java.time.LocalDate;
import java.util.Objects;

public class LoanDto {
    private long loanID;
    private LibraryUser loanTaker;
    private Book book;
    private LocalDate loanDate;
    private boolean terminated;

    /**
     * default constructor
     */
    public LoanDto() {
    }

    /**
     * Constructor without id
     * @param loanTaker LibraryUser
     * @param book Book
     * @param loanDate LocalDate
     * @param terminated boolean
     */
    public LoanDto(LibraryUser loanTaker, Book book, LocalDate loanDate, boolean terminated) {
        this.loanTaker = loanTaker;
        this.book = book;
        this.loanDate = loanDate;
        this.terminated = terminated;
    }

    /**
     * Constructor with id
     * @param
     * @param loanTaker LibraryUser
     * @param book Book
     * @param loanDate LocalDate
     * @param terminated boolean
     */
    public LoanDto(long loanID, LibraryUser loanTaker, Book book, LocalDate loanDate, boolean terminated) {
        this.loanID = loanID;
        this.loanTaker = loanTaker;
        this.book = book;
        this.loanDate = loanDate;
        this.terminated = terminated;
    }

    /**
     *
     * @return long
     */
    public long getLoanID() {
        return loanID;
    }

    /**
     *
     * @param loanID long
     */
    public void setLoanID(long loanID) {
        this.loanID = loanID;
    }

    /**
     *
     * @return LibraryUser
     */
    public LibraryUser getLoanTaker() {
        return loanTaker;
    }

    /**
     *
     * @param loanTaker LibraryUser
     */
    public void setLoanTaker(LibraryUser loanTaker) {
        this.loanTaker = loanTaker;
    }

    /**
     *
     * @return Book
     */
    public Book getBook() {
        return book;
    }

    /**
     *
     * @param book Book
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     *
     * @return LocalDate
     */
    public LocalDate getLoanDate() {
        return loanDate;
    }

    /**
     *
     * @param loanDate LocalDate
     */
    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    /**
     *
     * @return boolean
     */
    public boolean isTerminated() {
        return terminated;
    }

    /**
     *
     * @param terminated boolean
     */
    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }

    /**
     *
     * @param o Object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanDto loanDto = (LoanDto) o;
        return loanID == loanDto.loanID &&
                terminated == loanDto.terminated &&
                Objects.equals(loanTaker, loanDto.loanTaker) &&
                Objects.equals(book, loanDto.book) &&
                Objects.equals(loanDate, loanDto.loanDate);
    }

    /**
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(loanID, loanTaker, book, loanDate, terminated);
    }

    /**
     *
     * @return String
     */
    @Override
    public String toString() {
        return "LoanDto{" + "loanID=" + loanID +
                ", loanTaker=" + loanTaker +
                ", book=" + book +
                ", loanDate=" + loanDate +
                ", terminated=" + terminated +
                '}';
    }
}
