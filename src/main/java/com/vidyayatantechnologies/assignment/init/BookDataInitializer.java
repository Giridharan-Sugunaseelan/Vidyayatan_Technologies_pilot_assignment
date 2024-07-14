package com.vidyayatantechnologies.assignment.init;

import com.vidyayatantechnologies.assignment.entities.Book;
import com.vidyayatantechnologies.assignment.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookDataInitializer implements CommandLineRunner {

    private BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        initiateBooks();
    }

    public void initiateBooks(){
        createBooksIfNotExists("Thirukkural", "Thiruvalluvar", true);
        createBooksIfNotExists("Atomic Habits", "James Clear", true);
        createBooksIfNotExists("Think Straight", "Darius Foroux", true);
        createBooksIfNotExists("Deep Work", "Cal NewPort", true);
        createBooksIfNotExists("Da Vinci Code", "Dan Brown", true);
        createBooksIfNotExists("Harry Potter", "J.K Rowling", true);

    }

    public void createBooksIfNotExists(String bookTitle, String author, Boolean availability){
        if(!this.bookRepository.existsByBookTitle(bookTitle)){
            this.bookRepository.save(new Book(bookTitle, author, availability));
        }
    }
}
