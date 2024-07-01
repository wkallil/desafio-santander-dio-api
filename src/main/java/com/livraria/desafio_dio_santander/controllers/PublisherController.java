package com.livraria.desafio_dio_santander.controllers;


import com.livraria.desafio_dio_santander.dtos.PublisherRecordDto;
import com.livraria.desafio_dio_santander.models.PublisherModel;
import com.livraria.desafio_dio_santander.services.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping
    public ResponseEntity<PublisherModel> savePublisher(@RequestBody PublisherRecordDto publisherRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherService.savePublisher(publisherRecordDto));
    }

    @GetMapping
    public ResponseEntity<List<PublisherModel>> getAllPublishers() {
        return ResponseEntity.ok(publisherService.getAllPublishers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePublisher(@PathVariable UUID id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.ok("Editora deletada com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherModel> getByIdPublisher(@PathVariable UUID id) {
        return ResponseEntity.ok(publisherService.findByIdPublisher(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PublisherModel> updatePublisher(@PathVariable UUID id, @RequestBody PublisherRecordDto publisherRecordDto) {
        return ResponseEntity.ok(publisherService.updatePublisher(id, publisherRecordDto));
    }
}
