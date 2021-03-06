package se.lexicon.michelle.booklender.dto;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

public class LibraryUserDto {

    public static final String EMAIL_PATTERN = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";

    @Null (message = "Loan id should not be present.")
    private int userId;

    @PastOrPresent(message = "A registration date cannot be in the future")
    private LocalDate regDate;

    @NotBlank(message = "Name is obligatory")
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank(message = "email is obligatory")
    @Email(regexp = EMAIL_PATTERN)
    private String email;

    /**
     * Default Constructor
     */
    public LibraryUserDto() {
    }

    /**
     * Constructor without id
     * @param regDate LocalDate
     * @param name String
     * @param email String
     */
    public LibraryUserDto(LocalDate regDate, String name, String email) {
        this.regDate = regDate;
        this.name = name;
        this.email = email;
    }

    /**
     * Constructor with id
     * @param userId int
     * @param regDate LocalDate
     * @param name String
     * @param email String
     */
    public LibraryUserDto(int userId, LocalDate regDate, String name, String email) {
        this.userId = userId;
        this.regDate = regDate;
        this.name = name;
        this.email = email;
    }

    /**
     *
     * @return int
     */
    public int getUserId() {
        return userId;
    }

    /**
     *
     * @return LocalDate
     */
    public LocalDate getRegDate() {
        return regDate;
    }

    /**
     *
     * @param regDate LocalDate
     */
    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    /**
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return String
     */
    @Override
    public String toString() {
        return "LibraryUserDto{" + "userId=" + userId +
                ", regDate=" + regDate +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
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
        LibraryUserDto that = (LibraryUserDto) o;
        return userId == that.userId &&
                Objects.equals(regDate, that.regDate) &&
                Objects.equals(name, that.name) &&
                Objects.equals(email, that.email);
    }

    /**
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(userId, regDate, name, email);
    }
}
