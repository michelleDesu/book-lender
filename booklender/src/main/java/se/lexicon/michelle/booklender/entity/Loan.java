package se.lexicon.michelle.booklender.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;


import static java.time.temporal.ChronoUnit.DAYS;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long loanID;

    @ManyToOne(
            cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.EAGER
    )
    private LibraryUser loanTaker;

    @ManyToOne(
            cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.EAGER
    )
    private Book book;

    private LocalDate loanDate;

    @Column(name="isTerminated")
    private boolean terminated;

    public Loan() {
    }

    public Loan(LibraryUser loanTaker, Book book, LocalDate loanDate, boolean terminated) {
        this.loanTaker = loanTaker;
        this.book = book;
        this.loanDate = loanDate;
        this.terminated = terminated;
    }

    public long getLoanID() {
        return loanID;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
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

    public boolean isOverdue() {
        LocalDate now = LocalDate.now();
        int loanDays = getLoanDays(loanDate, now);

        return loanDays > book.getMaxLoanDays();
    }

    private int getLoanDays( LocalDate loanDate,  LocalDate newDate) {

        return (int)DAYS.between(loanDate, newDate);
    }

    public BigDecimal getFine(){

        BigDecimal daysOverdue = BigDecimal.valueOf(getLoanDays(loanDate, LocalDate.now())).subtract(BigDecimal.valueOf(book.getMaxLoanDays()) );
        return book.getFinePerDay().multiply( daysOverdue).setScale(2, RoundingMode.HALF_UP);
    }


    public LocalDate getLoanDate() {
        return loanDate;
    }

    public boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }

    public boolean extendLoan(int days){

        if (days > book.getMaxLoanDays()){
            return false;
        }

        LocalDate newLoanDate =  LocalDate.now();

        if(book.isReserved() || isOverdue()){
            return false;
        }
        else if(getLoanDays(loanDate, newLoanDate) <= book.getMaxLoanDays()  ){
            loanDate = newLoanDate;
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return loanID == loan.loanID &&
                terminated == loan.terminated &&
                Objects.equals(loanTaker, loan.loanTaker) &&
                Objects.equals(book, loan.book) &&
                Objects.equals(loanDate, loan.loanDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanID, loanTaker, book, loanDate, terminated);
    }

    @Override
    public String toString() {
        return "Loan{" + "loanID=" + loanID +
                ", loanTaker=" + loanTaker +
                ", book=" + book +
                ", loanDate=" + loanDate +
                ", terminated=" + terminated +
                '}';
    }


}
