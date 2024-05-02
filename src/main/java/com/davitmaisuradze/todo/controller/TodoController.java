package com.davitmaisuradze.todo.controller;

import com.davitmaisuradze.todo.dto.todo.NewTodoDto;
import com.davitmaisuradze.todo.dto.todo.TodoDto;
import com.davitmaisuradze.todo.dto.todo.TodoInfoDto;
import com.davitmaisuradze.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/{username}")
    public ResponseEntity<TodoDto> createTodo(
            @PathVariable("username") String username,
            @RequestBody NewTodoDto newTodoDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTask(username, newTodoDto));
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<TodoInfoDto>> findTodosByUsername(
            @PathVariable("username") String username
    ) {
        return ResponseEntity.ok(todoService.findTodosByUsername(username));
    }

    @PutMapping("/{username}/{title}")
    public ResponseEntity<TodoInfoDto> updateTodo(
            @PathVariable String username,
            @PathVariable String title,
            @RequestBody NewTodoDto newTodoDto
    ) {
        return ResponseEntity.ok(todoService.updateTodo(username, title, newTodoDto));
    }

    @DeleteMapping("/{username}/{title}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable String username,
            @PathVariable String title
    ) {
        todoService.delete(username, title);
        return ResponseEntity.noContent().build();
    }
}
