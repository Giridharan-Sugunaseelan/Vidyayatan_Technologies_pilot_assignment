package com.vidyayatantechnologies.assignment.service;

import com.vidyayatantechnologies.assignment.dto.BookDto;
import com.vidyayatantechnologies.assignment.entities.Book;
import com.vidyayatantechnologies.assignment.entities.User;
import com.vidyayatantechnologies.assignment.exception.BookNotFoundException;
import com.vidyayatantechnologies.assignment.repository.BookRepository;
import com.vidyayatantechnologies.assignment.repository.UserRepository;
import com.vidyayatantechnologies.assignment.util.Principal;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;

    private ModelMapper modelMapper;

    private UserRepository userRepository;

    public BookDto addBook(BookDto dto){
        Book book = modelMapper.map(dto, Book.class);
        book.setAvailable(true);
        Book addedBook = this.bookRepository.save(book);
        return modelMapper.map(addedBook, BookDto.class);
    }

    public BookDto getBook(Integer book_id){
        Book book = this.bookRepository.findById(book_id).orElseThrow(() -> new BookNotFoundException("Book Not Found"));
        return modelMapper.map(book, BookDto.class);
    }

    public List<BookDto> getAllBooks(){
        List<Book> books = this.bookRepository.findAll();
        return books.stream().map((book) -> this.modelMapper.map(book, BookDto.class)).toList();
    }

    public BookDto updateBook(BookDto dto, Integer book_id){
        Book oldBook = this.modelMapper.map(dto, Book.class);
        Book newBook = this.bookRepository.findById(book_id).orElseThrow(() -> new BookNotFoundException("Book Not Found"));
        newBook.setBookTitle(oldBook.getBookTitle());
        newBook.setAuthor(oldBook.getAuthor());
        newBook.setAvailable(oldBook.getAvailable());
        Book updatedBook = this.bookRepository.save(oldBook);
        return this.modelMapper.map(updatedBook, BookDto.class);
    }


    public BookDto borrowBook(Integer book_id){
        Book book = this.bookRepository.findById(book_id).orElseThrow(() -> new BookNotFoundException("Book not found!"));
        String email = Principal.getAuthentication().getName();
        User user = this.userRepository.findByEmail(email);
        user.getMyBooks().add(book);
        this.userRepository.save(user);
        book.setAvailable(false);
        book.setUser(user);
        Book saved = this.bookRepository.save(book);
        return this.modelMapper.map(saved, BookDto.class);
    }

    public BookDto returnBook(Integer book_id){
        Book book = this.bookRepository.findById(book_id).orElseThrow(() -> new BookNotFoundException("Book not found!"));
        String email = Principal.getAuthentication().getName();
        User user = this.userRepository.findByEmail(email);
        Set<Book> myBooks = user.getMyBooks();
        myBooks= myBooks.stream().filter((myBook) -> book.getBook_id() != myBook.getBook_id()).collect(Collectors.toSet());
        user.setMyBooks(myBooks);
        this.userRepository.save(user);
        book.setAvailable(true);
        book.setUser(null);
        Book saved = this.bookRepository.save(book);
        return this.modelMapper.map(saved, BookDto.class);
    }

    public boolean deleteBook(Integer book_id){
        this.bookRepository.deleteById(book_id);
        return true;
    }

}
