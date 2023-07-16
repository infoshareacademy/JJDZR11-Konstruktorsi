package pl.isa.biblioteka.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.type.YesNoConverter;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @JsonProperty("kind")
    @Column(name = "category", nullable = false)
    private String category;

    @Convert(converter = YesNoConverter.class)
    private boolean state = true;

//    @CreationTimestamp
    @Column(name = "borrowing_date")
    private String borrowingDate;

//    @CreationTimestamp
    @Column(name = "return_date")
    private String returnDate;

    @Column(name = "counter", nullable = false)
    private Integer counter;

    @PrePersist
    public void setDefaultValues() {
        if (counter == null) {
            counter = 0;
        }
    }

    @JsonManagedReference
    @ManyToOne
    private User user;

    public Book() {
    }

    public Book(Integer counter) {
        this.counter = counter;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public Book(String title, String author, String category) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.state = true;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book(String title, String author, String category, boolean state) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.state = state;
    }

    public Book(String title, String author, String category, boolean state, String borrowingDate, String returnDate, Integer counter) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.state = state;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
        this.counter = counter;
    }

    public String getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(String borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getPerson() {
        return user;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Book{" + "title='" + title + '\'' + ", author='" + author + '\'' + ", category='" + category + '\'' + ", state=" + state + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}