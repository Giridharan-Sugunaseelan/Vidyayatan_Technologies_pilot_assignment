package com.vidyayatantechnologies.assignment.controller;

import com.vidyayatantechnologies.assignment.annotations.Permissions;
import com.vidyayatantechnologies.assignment.dto.BookDto;
import com.vidyayatantechnologies.assignment.enums.LogicEnum;
import com.vidyayatantechnologies.assignment.enums.PermissionsEnum;
import com.vidyayatantechnologies.assignment.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    @Permissions(permissions = {PermissionsEnum.AllowWrite, PermissionsEnum.AllowUpdate}, type = LogicEnum.ANY)
    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto dto){
        BookDto bookDto = this.bookService.addBook(dto);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @Permissions(permissions = {PermissionsEnum.AllowRead, PermissionsEnum.AllowBorrow, PermissionsEnum.AllowReturn},type = LogicEnum.ANY)
    @GetMapping("/{book_id}")
    public ResponseEntity<BookDto> getBook(@PathVariable Integer book_id){
        BookDto book = this.bookService.getBook(book_id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @Permissions(permissions = {PermissionsEnum.AllowRead, PermissionsEnum.AllowBorrow, PermissionsEnum.AllowReturn},type = LogicEnum.ANY)
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(){
        List<BookDto> allBooks = this.bookService.getAllBooks();
        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }

    @Permissions(permissions = {PermissionsEnum.AllowWrite, PermissionsEnum.AllowUpdate}, type = LogicEnum.ANY)
    @PutMapping("/{book_id}")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto dto, @PathVariable Integer book_id){
        BookDto updatedBook = this.bookService.updateBook(dto, book_id);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @Permissions(permissions = {PermissionsEnum.AllowRead, PermissionsEnum.AllowBorrow, PermissionsEnum.AllowReturn},type = LogicEnum.ANY)
    @PutMapping("/borrow/{book_id}")
    public ResponseEntity<BookDto> borrowBook(@PathVariable Integer book_id){
        BookDto bookDto = this.bookService.borrowBook(book_id);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @Permissions(permissions = {PermissionsEnum.AllowRead, PermissionsEnum.AllowBorrow, PermissionsEnum.AllowReturn},type = LogicEnum.ANY)
    @PutMapping("/return/{book_id}")
    public ResponseEntity<BookDto> returnBook(@PathVariable Integer book_id){
        BookDto bookDto = this.bookService.returnBook(book_id);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @Permissions(permissions = PermissionsEnum.AllowDelete)
    @DeleteMapping("/{book_id}")
    public ResponseEntity<Boolean> deleteBook(@PathVariable Integer book_id){
        boolean status = this.bookService.deleteBook(book_id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
