package com.vidyayatantechnologies.assignment.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer book_id;

    @Column(nullable = false)
    private String bookTitle;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private Boolean available;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "userId", referencedColumnName = "user_id")
    private User user;

    public Book(String bookTitle, String author, Boolean available) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(book_id, book.book_id) && Objects.equals(bookTitle, book.bookTitle) && Objects.equals(author, book.author) && Objects.equals(available, book.available);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book_id, bookTitle, author, available);
    }
}
