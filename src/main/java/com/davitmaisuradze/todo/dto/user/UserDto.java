package com.davitmaisuradze.todo.dto.user;

import com.davitmaisuradze.todo.dto.todo.TodoInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private List<TodoInfoDto> todos;
}
