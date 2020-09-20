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

    public LoanDto() {
    }

    public LoanDto(LibraryUser loanTaker, Book book, LocalDate loanDate, boolean terminated) {
        this.loanTaker = loanTaker;
        this.book = book;
        this.loanDate = loanDate;
        this.terminated = terminated;
    }

    public LoanDto(long loanID, LibraryUser loanTaker, Book book, LocalDate loanDate, boolean terminated) {
        this.loanID = loanID;
        this.loanTaker = loanTaker;
        this.book = book;
        this.loanDate = loanDate;
        this.terminated = terminated;
    }

    public long getLoanID() {
        return loanID;
    }

    public void setLoanID(long loanID) {
        this.loanID = loanID;
    }

    public LibraryUser getLoanTaker() {
        return loanTaker;
    }

    public void setLoanTaker(LibraryUser loanTaker) {
        this.loanTaker = loanTaker;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(loanID, loanTaker, book, loanDate, terminated);
    }

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
