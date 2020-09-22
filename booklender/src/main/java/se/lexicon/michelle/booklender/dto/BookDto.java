package se.lexicon.michelle.booklender.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class BookDto {
    private int bookId;
    private String title;
    private boolean available;
    private boolean reserved;
    private int maxLoanDays;
    private BigDecimal finePerDay;
    private String description;

    /**
     * Default constructor
     */
    public BookDto() {
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
    public BookDto(String title, boolean available, boolean reserved, int maxLoanDays, BigDecimal finePerDay, String description) {
        this.title = title;
        this.available = available;
        this.reserved = reserved;
        this.maxLoanDays = maxLoanDays;
        this.finePerDay = finePerDay;
        this.description = description;
    }

    /**
     * Constructor with id
     * @param bookId int
     * @param title String
     * @param available boolean
     * @param reserved boolean
     * @param maxLoanDays int
     * @param finePerDay BigDecimal
     * @param description String
     */
    public BookDto(int bookId, String title, boolean available, boolean reserved, int maxLoanDays, BigDecimal finePerDay, String description) {
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
     * @returnint
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
        BookDto bookDto = (BookDto) o;
        return bookId == bookDto.bookId &&
                available == bookDto.available &&
                reserved == bookDto.reserved &&
                maxLoanDays == bookDto.maxLoanDays &&
                Objects.equals(title, bookDto.title) &&
                Objects.equals(finePerDay, bookDto.finePerDay) &&
                Objects.equals(description, bookDto.description);
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
        return "BookDto{" + "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", available=" + available +
                ", reserved=" + reserved +
                ", maxLoanDays=" + maxLoanDays +
                ", finePerDay=" + finePerDay +
                ", description='" + description + '\'' +
                '}';
    }
}
