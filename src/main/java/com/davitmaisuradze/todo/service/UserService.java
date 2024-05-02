package com.davitmaisuradze.todo.service;

import com.davitmaisuradze.todo.dto.user.CreateUserDto;
import com.davitmaisuradze.todo.dto.user.UserDto;

public interface UserService {
    UserDto create(CreateUserDto createUserDto);
}
