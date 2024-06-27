package com.livraria.desafio_dio_santander.repositories;

import com.livraria.desafio_dio_santander.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<BookModel, UUID> {

}
