package com.rafaelsteffens.controller;

import com.rafaelsteffens.entity.Todo;
import com.rafaelsteffens.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")

@RequestMapping("/todos")
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<List<Todo>> create(@RequestBody @Valid Todo todo) {
        List<Todo> todos = todoService.create(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(todos);

    }

    @GetMapping
    List<Todo> list() {
        return todoService.list();
    }

    @PutMapping("{id}")
    public ResponseEntity<List<Todo>>
        update(@PathVariable Long id, @RequestBody Todo todo) {
            List<Todo> todos = todoService.update(id, todo);
            return ResponseEntity.ok().body(todos);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<List<Todo>> delete(@PathVariable("id") Long id) {
        List<Todo> todos = todoService.delete(id);
        return ResponseEntity.ok().body(todos);
    }
}