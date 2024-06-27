package com.livraria.desafio_dio_santander.controllers;

import com.livraria.desafio_dio_santander.dtos.BookRecordDto;
import com.livraria.desafio_dio_santander.models.BookModel;
import com.livraria.desafio_dio_santander.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookModel> saveBook(@RequestBody BookRecordDto bookRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookRecordDto));
    }

    @GetMapping
    public ResponseEntity<List<BookModel>> getAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable UUID id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookModel> getByIdBook(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findById(id));
    }


}
