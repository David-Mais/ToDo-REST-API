package com.davitmaisuradze.todo.mapper;

import com.davitmaisuradze.todo.dto.todo.TodoInfoDto;
import com.davitmaisuradze.todo.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);
    TodoInfoDto todoEntityToTodoInfoDto(Todo todo);
}
