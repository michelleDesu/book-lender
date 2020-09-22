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

    /**
     * default constructor
     */
    public Loan() {
    }

    /**
     * Constructor without id
     * @param loanTaker LibraryUser
     * @param book Book
     * @param loanDate LocalDate
     * @param terminated boolean
     */
    public Loan(LibraryUser loanTaker, Book book, LocalDate loanDate, boolean terminated) {
        this.loanTaker = loanTaker;
        this.book = book;
        this.loanDate = loanDate;
        this.terminated = terminated;
    }

    /**
     * Constructor with loanId
     * @param loanID long
     * @param loanTaker LibraryUser
     * @param book Book
     * @param loanDate LocalDate
     * @param terminated boolean
     */
    public Loan(long loanID, LibraryUser loanTaker, Book book, LocalDate loanDate, boolean terminated) {
        this.loanID = loanID;
        this.loanTaker = loanTaker;
        this.book = book;
        this.loanDate = loanDate;
        this.terminated = terminated;
    }

    /**
     * Returns the loan Id
     * @return long
     */
    public long getLoanID() {
        return loanID;
    }

    /**
     * sets the loanDate
     * @param loanDate LocalDate
     */
    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    /**
     * Returns the libraryUser
     * @return LibraryUser
     */
    public LibraryUser getLoanTaker() {
        return loanTaker;
    }

    /**
     * Sets the LibraryUser
     * @param loanTaker LibraryUser
     */
    public void setLoanTaker(LibraryUser loanTaker) {
        this.loanTaker = loanTaker;
    }

    /**
     * Returns Book
     * @return Book
     */
    public Book getBook() {
        return book;
    }

    /**
     * Set a book
     * @param book Book
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Returns true if loanDays is bigger than a books maxLoanDays
     * @return boolean
     */
    public boolean isOverdue() {
        LocalDate now = LocalDate.now();
        int loanDays = getLoanDays(loanDate, now);

        return loanDays > book.getMaxLoanDays();
    }

    /**
     * Returns the amount of days between today and the loan day
     * @param loanDate LocalDate
     * @param newDate LocalDate
     * @return int
     */
    private int getLoanDays( LocalDate loanDate,  LocalDate newDate) {

        return (int)DAYS.between(loanDate, newDate);
    }

    /**
     * Returns the fine based on fine per day and days overdue
     * @return BigDecimal
     */
    public BigDecimal getFine(){

        BigDecimal daysOverdue = BigDecimal.valueOf(getLoanDays(loanDate, LocalDate.now())).subtract(BigDecimal.valueOf(book.getMaxLoanDays()) );
        return book.getFinePerDay().multiply( daysOverdue).setScale(2, RoundingMode.HALF_UP);
    }


    /**
     * Returns the loanDate
     * @return LocalDate
     */
    public LocalDate getLoanDate() {
        return loanDate;
    }

    /**
     * Returns true if terminated, false if not
     * @return Boolean
     */
    public boolean isTerminated() {
        return terminated;
    }

    /**
     * Sets value of Terminated
     * @param terminated boolean
     */
    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }

    /**
     * extends the loan if possible, sets the new loan date
     * from today.
     * If reserved or overdue it is not possible to extend the loan.
     * @param days int
     * @return boolean
     */
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

    /**
     * equals method
     * @param o object
     * @return boolean
     */
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

    /**
     * hashcode method
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(loanID, loanTaker, book, loanDate, terminated);
    }

    /**
     * ToString method
     * @return String
     */
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
