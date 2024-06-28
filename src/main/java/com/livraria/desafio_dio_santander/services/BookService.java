package com.livraria.desafio_dio_santander.services;

import com.livraria.desafio_dio_santander.dtos.BookRecordDto;
import com.livraria.desafio_dio_santander.models.AuthorModel;
import com.livraria.desafio_dio_santander.models.BookModel;
import com.livraria.desafio_dio_santander.models.ReviewModel;
import com.livraria.desafio_dio_santander.repositories.AuthorRepository;
import com.livraria.desafio_dio_santander.repositories.BookRepository;
import com.livraria.desafio_dio_santander.repositories.PublisherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
        book.setAuthors(new HashSet<>(authorRepository.findAllById(bookRecordDto.authorIds())));

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

    @Transactional
    public BookModel updateBook(UUID id, BookRecordDto bookRecordDto) {
        BookModel book = bookRepository.findById(id).orElseThrow(NoSuchElementException::new);

        if (bookRecordDto.title() != null) {
            book.setTitle(bookRecordDto.title());
        }

        if (bookRecordDto.publisherId() != null) {
            book.setPublisher(publisherRepository
                    .findById(bookRecordDto.publisherId())
                    .orElseThrow(NoSuchElementException::new));
        }

        if (bookRecordDto.authorIds() != null && !bookRecordDto.authorIds().isEmpty()) {
            book.setAuthors(new HashSet<>(authorRepository.findAllById(bookRecordDto.authorIds())));
        }

        if (bookRecordDto.reviewComment() != null) {
            ReviewModel review = book.getReview();
            if (review == null) {
                review = new ReviewModel();
                review.setBook(book);
                book.setReview(review);
            }
            review.setComment(bookRecordDto.reviewComment());
        }
        return bookRepository.save(book);

    }

}
