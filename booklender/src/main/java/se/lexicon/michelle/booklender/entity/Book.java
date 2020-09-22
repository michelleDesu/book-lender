package se.lexicon.michelle.booklender.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    private String title;
    private boolean available;
    private boolean reserved;
    private int maxLoanDays;
    private BigDecimal finePerDay;
    private String description;

    /**
     * default constructor
     */
    public Book(){
    }

    /**
     * Constructor without id
     * @param title String
     * @param available boolean
     * @param reserved boolean
     * @param maxLoanDays int
     * @param finePerDay BigDecimal
     * @param description String
     */
    public Book(String title, boolean available, boolean reserved, int maxLoanDays, BigDecimal finePerDay, String description) {
        this.title = title;
        this.available = available;
        this.reserved = reserved;
        this.maxLoanDays = maxLoanDays;
        this.finePerDay = finePerDay;
        this.description = description;
    }

    /**
     * Constructor without id
     * @param bookId int
     * @param title String
     * @param available boolean
     * @param reserved boolean
     * @param maxLoanDays int
     * @param finePerDay BigDecimal
     * @param description String
     */
    public Book(int bookId, String title, boolean available, boolean reserved, int maxLoanDays, BigDecimal finePerDay, String description) {
        this.bookId = bookId;
        this.title = title;
        this.available = available;
        this.reserved = reserved;
        this.maxLoanDays = maxLoanDays;
        this.finePerDay = finePerDay;
        this.description = description;
    }

    /**
     *
     * @return int
     */
    public int getBookId() {
        return bookId;
    }

    /**
     *
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title String
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return boolean
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     *
     * @param available boolean
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     *
     * @return boolean
     */
    public boolean isReserved() {
        return reserved;
    }

    /**
     *
     * @param reserved boolean
     */
    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    /**
     *
     * @return int
     */
    public int getMaxLoanDays() {
        return maxLoanDays;
    }

    /**
     *
     * @param maxLoanDays int
     */
    public void setMaxLoanDays(int maxLoanDays) {
        this.maxLoanDays = maxLoanDays;
    }

    /**
     *
     * @return BigDecimal
     */
    public BigDecimal getFinePerDay() {
        return finePerDay;
    }

    /**
     *
     * @param finePerDay BigDecimal
     */
    public void setFinePerDay(BigDecimal finePerDay) {
        this.finePerDay = finePerDay;
    }

    /**
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description String
     */
    public void setDescription(String description) {
        this.description = description;
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
        Book book = (Book) o;
        return bookId == book.bookId &&
                available == book.available &&
                reserved == book.reserved &&
                maxLoanDays == book.maxLoanDays &&
                Objects.equals(title, book.title) &&
                Objects.equals(finePerDay, book.finePerDay) &&
                Objects.equals(description, book.description);
    }

    /**
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, available, reserved, maxLoanDays, finePerDay, description);
    }

    /**
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Book{" + "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", available=" + available +
                ", reserved=" + reserved +
                ", maxLoanDays=" + maxLoanDays +
                ", finePerDay=" + finePerDay +
                ", description='" + description + '\'' +
                '}';
    }
}
