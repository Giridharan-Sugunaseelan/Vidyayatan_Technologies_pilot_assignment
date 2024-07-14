package com.vidyayatantechnologies.assignment.repository;

import com.vidyayatantechnologies.assignment.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    boolean existsByBookTitle(String bookTitle);


}
