package com.davitmaisuradze.todo.dto.user;

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
public class UserInfoDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
}
