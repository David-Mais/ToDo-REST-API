package com.davitmaisuradze.todo.service.impl;

import com.davitmaisuradze.todo.dto.todo.NewTodoDto;
import com.davitmaisuradze.todo.dto.todo.TodoDto;
import com.davitmaisuradze.todo.dto.todo.TodoInfoDto;
import com.davitmaisuradze.todo.dto.user.UserInfoDto;
import com.davitmaisuradze.todo.entity.Todo;
import com.davitmaisuradze.todo.entity.User;
import com.davitmaisuradze.todo.exception.TodoException;
import com.davitmaisuradze.todo.mapper.TodoMapper;
import com.davitmaisuradze.todo.repository.TodoRepository;
import com.davitmaisuradze.todo.repository.UserRepository;
import com.davitmaisuradze.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    @Override
    public TodoDto createTask(String username, NewTodoDto newTodoDto) {
        User user = userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            throw new TodoException("User not found", "404");
        }

        if (todoExists(newTodoDto.getTitle(), username)) {
            throw new TodoException("Todo already exists", "400");
        }

        UserInfoDto userInfoDto = UserInfoDto
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();

        Todo todo = Todo
                .builder()
                .title(newTodoDto.getTitle())
                .description(newTodoDto.getDescription())
                .user(user)
                .build();
        todo = todoRepository.save(todo);

        return TodoDto
                .builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .user(userInfoDto)
                .build();
    }

    @Override
    public List<TodoInfoDto> findTodosByUsername(String username) {
        if (!usernameExists(username)) {
            throw new TodoException("User not found", "404");
        }
        List<Todo> todos = todoRepository.findByUserUsername(username);
        List<TodoInfoDto> todoInfoDtos = new ArrayList<>();

        todos
                .forEach(todo -> todoInfoDtos.add(todoMapper.todoEntityToTodoInfoDto(todo)));

        return todoInfoDtos;
    }

    @Override
    public TodoInfoDto updateTodo(String username, String title, NewTodoDto newTodoDto) {
        Todo todo = todoRepository.findByTitleAndUsername(title, username).orElse(null);

        if (todo == null) {
            throw new TodoException("Todo not found", "404");
        }

        todo.setTitle(newTodoDto.getTitle());
        todo.setDescription(newTodoDto.getDescription());
        todo = todoRepository.save(todo);
        return todoMapper.todoEntityToTodoInfoDto(todo);
    }

    @Override
    public void delete(String username, String title) {
        Todo todo = todoRepository.findByTitleAndUsername(title, username).orElse(null);
        if (todo == null) {
            throw new TodoException("Todo not found", "404");
        }
        todoRepository.delete(todo);
    }

    private boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    private boolean todoExists(String title, String username) {
        return todoRepository.findByTitleAndUsername(title, username).isPresent();
    }
}
