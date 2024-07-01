package com.livraria.desafio_dio_santander.services;


import com.livraria.desafio_dio_santander.dtos.AuthorRecordDto;
import com.livraria.desafio_dio_santander.models.AuthorModel;
import com.livraria.desafio_dio_santander.repositories.AuthorRepository;
import com.livraria.desafio_dio_santander.repositories.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public AuthorService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional
    public AuthorModel saveAuthor(AuthorRecordDto authorRecordDto) {
        AuthorModel author = new AuthorModel();
        author.setName(authorRecordDto.name());
        author.setBooks(new HashSet<>(bookRepository.findAllById(authorRecordDto.booksId())));
        return authorRepository.save(author);
    }

    public List<AuthorModel> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Transactional
    public AuthorModel findByIdAuthor(UUID id) {
        return authorRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public void deleteAuthor(UUID id) {
        authorRepository.deleteById(id);
    }

    @Transactional
    public AuthorModel updateAuthor(UUID id, AuthorRecordDto authorRecordDto) {
        AuthorModel author = authorRepository.findById(id).orElseThrow(NoSuchElementException::new);

        if (authorRecordDto.name() != null) {
            author.setName(authorRecordDto.name());
        }

        if (authorRecordDto.booksId() != null && authorRecordDto.booksId().isEmpty()) {
            author.setBooks(new HashSet<>(bookRepository.findAllById(authorRecordDto.booksId())));
        }
        return authorRepository.save(author);
    }
}
