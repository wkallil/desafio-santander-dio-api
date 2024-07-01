package com.livraria.desafio_dio_santander.dtos;

import java.util.Set;
import java.util.UUID;

public record AuthorRecordDto(String name, Set<UUID> booksId) {
}
