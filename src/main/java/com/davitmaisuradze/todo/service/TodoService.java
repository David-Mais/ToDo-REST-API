package com.davitmaisuradze.todo.service;


import com.davitmaisuradze.todo.dto.todo.NewTodoDto;
import com.davitmaisuradze.todo.dto.todo.TodoDto;
import com.davitmaisuradze.todo.dto.todo.TodoInfoDto;

import java.util.List;

public interface TodoService {
    TodoDto createTask(String username, NewTodoDto newTodoDto);
    List<TodoInfoDto> findTodosByUsername(String username);
    TodoInfoDto updateTodo(String username, String title, NewTodoDto newTodoDto);
    void delete(String username, String title);
}
