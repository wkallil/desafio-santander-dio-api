package com.livraria.desafio_dio_santander.repositories;

import com.livraria.desafio_dio_santander.models.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorModel, UUID> {
}
