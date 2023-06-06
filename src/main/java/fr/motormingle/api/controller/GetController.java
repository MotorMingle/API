package fr.motormingle.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface GetController<T, I> {

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<T>> getAll();

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<T> getById(@PathVariable("id") I id);
}
