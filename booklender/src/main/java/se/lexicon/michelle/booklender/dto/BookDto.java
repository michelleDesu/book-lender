package se.lexicon.michelle.booklender.dto;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class BookDto {
    @Null(message = "Book id should not be present")
    private int bookId;

    @NotBlank(message = "Title is obligatory")
    @Size(min = 2, max = 255)
    private String title;

    private boolean available;
    private boolean reserved;

    @Positive(message = "max loan days should be 1 or more days")
    private int maxLoanDays;
    @PositiveOrZero
    private BigDecimal finePerDay;

    @NotBlank(message = "description is obligatory")
    @Size(min = 2)
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
