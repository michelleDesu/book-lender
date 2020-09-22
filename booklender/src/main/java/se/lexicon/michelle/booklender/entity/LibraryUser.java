package se.lexicon.michelle.booklender.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class LibraryUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private LocalDate regDate;
    private String name;

    @Column(unique = true)
    private String email;

    /**
     * default constructor
     */
    public LibraryUser() {
    }

    /**
     * Constructor without id
     * @param regDate LocalDate
     * @param name String
     * @param email String
     */
    public LibraryUser(LocalDate regDate, String name, String email) {
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
    public LibraryUser(int userId, LocalDate regDate, String name, String email) {
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
     * @return LoLocalDate
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
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryUser that = (LibraryUser) o;
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

    /**
     *
     * @return String
     */
    @Override
    public String toString() {
        return "LibraryUser{" + "userId=" + userId +
                ", regDate=" + regDate +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
