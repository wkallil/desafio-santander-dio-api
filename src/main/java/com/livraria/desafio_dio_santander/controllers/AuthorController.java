package com.livraria.desafio_dio_santander.controllers;


import com.livraria.desafio_dio_santander.dtos.AuthorRecordDto;
import com.livraria.desafio_dio_santander.models.AuthorModel;
import com.livraria.desafio_dio_santander.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorModel> saveAuthor(@RequestBody AuthorRecordDto authorRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.saveAuthor(authorRecordDto));
    }

    @GetMapping
    public ResponseEntity<List<AuthorModel>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable UUID id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok("O Autor foi detetado");
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorModel> getByIdAuthor(@PathVariable UUID id) {
        return ResponseEntity.ok(authorService.findByIdAuthor(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AuthorModel> updateAuthor(@PathVariable UUID id, @RequestBody AuthorRecordDto authorRecordDto) {
        return ResponseEntity.ok(authorService.updateAuthor(id, authorRecordDto));
    }
}
