package com.davitmaisuradze.todo.service.impl;

import com.davitmaisuradze.todo.dto.user.CreateUserDto;
import com.davitmaisuradze.todo.dto.user.UserDto;
import com.davitmaisuradze.todo.entity.User;
import com.davitmaisuradze.todo.exception.TodoException;
import com.davitmaisuradze.todo.repository.UserRepository;
import com.davitmaisuradze.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto create(CreateUserDto createUserDto) {
        if (usernameExists(createUserDto.getUsername())) {
            throw new TodoException("Username already exists", "400");
        }
        User user = new User();
        user.setUsername(createUserDto.getUsername());
        user.setFirstName(createUserDto.getFirstName());
        user.setLastName(createUserDto.getLastName());
        user =  userRepository.save(user);

        return UserDto
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .todos(new ArrayList<>())
                .build();
    }

    private boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
