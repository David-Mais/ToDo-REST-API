package com.davitmaisuradze.todo.service;


import com.davitmaisuradze.todo.dto.todo.TodoDto;
import com.davitmaisuradze.todo.dto.todo.TodoInfoDto;

import java.util.List;

public interface TodoService {
    TodoDto createTask(String username, TodoInfoDto newTodoDto);
    List<TodoInfoDto> findTodosByUsername(String username);
    TodoInfoDto updateTodo(String username, String title, TodoInfoDto newTodoDto);
    void delete(String username, String title);
}
