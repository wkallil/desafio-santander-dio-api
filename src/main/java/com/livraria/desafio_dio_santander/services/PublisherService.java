package com.livraria.desafio_dio_santander.services;


import com.livraria.desafio_dio_santander.dtos.PublisherRecordDto;
import com.livraria.desafio_dio_santander.models.AuthorModel;
import com.livraria.desafio_dio_santander.models.PublisherModel;
import com.livraria.desafio_dio_santander.repositories.BookRepository;
import com.livraria.desafio_dio_santander.repositories.PublisherRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final BookRepository bookRepository;

    public PublisherService(PublisherRepository publisherRepository, BookRepository bookRepository) {
        this.publisherRepository = publisherRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public PublisherModel savePublisher(PublisherRecordDto publisherRecordDto) {
        PublisherModel publisher = new PublisherModel();
        publisher.setName(publisherRecordDto.name());
        publisher.setBooks(new HashSet<>(bookRepository.findAllById(publisherRecordDto.booksId())));
        return publisherRepository.save(publisher);
    }

    public List<PublisherModel> getAllPublishers(){
        return publisherRepository.findAll();
    }

    @Transactional
    public void deletePublisher(UUID id) {
        publisherRepository.deleteById(id);
    }

    @Transactional
    public PublisherModel findByIdPublisher(UUID id) {
        return publisherRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public PublisherModel updatePublisher(UUID id, PublisherRecordDto publisherRecordDto) {

        PublisherModel publisher = publisherRepository.findById(id).orElseThrow(NoSuchElementException::new);

        if (publisherRecordDto.name() != null) {
            publisher.setName(publisherRecordDto.name());
        }

        if (publisherRecordDto.booksId() != null && publisherRecordDto.booksId().isEmpty()) {
            publisher.setBooks(new HashSet<>(bookRepository.findAllById(publisherRecordDto.booksId())));
        }
        return publisherRepository.save(publisher);
    }
}
