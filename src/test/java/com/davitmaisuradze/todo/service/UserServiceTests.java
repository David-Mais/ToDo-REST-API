package com.davitmaisuradze.todo.service;

import com.davitmaisuradze.todo.dto.user.CreateUserDto;
import com.davitmaisuradze.todo.dto.user.UserDto;
import com.davitmaisuradze.todo.entity.User;
import com.davitmaisuradze.todo.exception.TodoException;
import com.davitmaisuradze.todo.repository.UserRepository;
import com.davitmaisuradze.todo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTests {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @Transactional
    void testCreateUser_WhenUserIsNew_ThenReturnUserDto() {
        String username = "test";
        User user = new User();
        user.setId(1L);
        user.setUsername(username);
        user.setFirstName("TestFirst");
        user.setLastName("TestLast");
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setUsername(username);
        createUserDto.setFirstName("TestFirst");
        createUserDto.setLastName("TestLast");

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDto userDto = userService.create(createUserDto);

        assertEquals(userDto.getUsername(), username);
        assertEquals(userDto.getFirstName(), createUserDto.getFirstName());
        assertEquals(userDto.getLastName(), createUserDto.getLastName());

        verify(userRepository, times(1)).save(any(User.class));
        verify(userRepository, times(1)).findByUsername(anyString());
    }

    @Test
    void testCreateUser_WhenUserAlreadyExists_ThenThrowException() {
        String username = "test";
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setUsername(username);
        createUserDto.setFirstName("TestFirst");
        createUserDto.setLastName("TestLast");

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(new User()));

        TodoException exception = assertThrows(
                TodoException.class,
                () -> userService.create(createUserDto)
        );

        assertTrue(exception.getMessage().contains("Username already exists"));
        assertEquals("400", exception.getErrorCode());
    }
}
