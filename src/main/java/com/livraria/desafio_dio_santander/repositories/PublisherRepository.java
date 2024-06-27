package com.livraria.desafio_dio_santander.repositories;

import com.livraria.desafio_dio_santander.models.PublisherModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PublisherRepository extends JpaRepository<PublisherModel, UUID> {
}
