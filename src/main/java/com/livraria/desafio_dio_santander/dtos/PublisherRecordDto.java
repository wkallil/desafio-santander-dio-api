package com.livraria.desafio_dio_santander.dtos;

import java.util.Set;
import java.util.UUID;

public record PublisherRecordDto(String name, Set<UUID> booksId) {
}
