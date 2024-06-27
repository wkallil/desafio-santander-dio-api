package com.livraria.desafio_dio_santander.repositories;

import com.livraria.desafio_dio_santander.models.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<ReviewModel, UUID> {
}
