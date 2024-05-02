package com.davitmaisuradze.todo.dto.todo;

import com.davitmaisuradze.todo.dto.user.UserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TodoDto {
    private Long id;
    private String title;
    private String description;
    private UserInfoDto user;
}
