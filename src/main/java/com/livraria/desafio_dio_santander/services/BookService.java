package com.livraria.desafio_dio_santander.services;

import com.livraria.desafio_dio_santander.dtos.BookRecordDto;
import com.livraria.desafio_dio_santander.models.BookModel;
import com.livraria.desafio_dio_santander.models.ReviewModel;
import com.livraria.desafio_dio_santander.repositories.AuthorRepository;
import com.livraria.desafio_dio_santander.repositories.BookRepository;
import com.livraria.desafio_dio_santander.repositories.PublisherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Transactional
    public BookModel saveBook(BookRecordDto bookRecordDto) {
        BookModel book = new BookModel();
        book.setTitle(bookRecordDto.title());
        book.setPublisher(publisherRepository.findById(bookRecordDto.publisherId()).get());
        book.setAuthors(authorRepository.findAllById(bookRecordDto.authorIds()).stream().collect(Collectors.toSet()));

        ReviewModel reviewModel = new ReviewModel();
        reviewModel.setComment(bookRecordDto.reviewComment());
        reviewModel.setBook(book);
        book.setReview(reviewModel);

        return bookRepository.save(book);
    }

    public List<BookModel> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public BookModel findById(UUID id) {
        return bookRepository.findById(id).orElseThrow(NoSuchElementException::new);

    }


}
