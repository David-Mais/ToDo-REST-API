package com.davitmaisuradze.todo.repository;

import com.davitmaisuradze.todo.entity.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("test")
@Sql(scripts = "/test-schema.sql")
class TodoRepositoryTests {
    @Autowired
    private TodoRepository todoRepository;

    @Test
    void testFindByUsername_WhenUsernameExists_ThenReturnTodos() {
        String username = "jdoe";
        List<Todo> todos = todoRepository.findByUserUsername(username);

        assertThat(todos).isNotEmpty().hasSize(3);
        assertThat(todos.get(0).getUser().getUsername()).isEqualTo(username);
    }

    @Test
    void testFindByUsername_WhenUsernameDoesNotExists_ThenReturnEmptyList() {
        String username = "NonExisting";
        List<Todo> todos = todoRepository.findByUserUsername(username);

        assertThat(todos).isEmpty();
    }

    @Test
    void testFindByTitleAndUsername_WhenBothExist_ThenReturnOptionalOfTodo() {
        String username = "jdoe";
        String title = "Task 1";

        Optional<Todo> todo = todoRepository.findByTitleAndUsername(title, username);

        assertThat(todo).isPresent();
    }

    @Test
    void testFindByTitleAndUsername_WhenTaskDoesNotExist_ThenReturnEmptyOptional() {
        String username = "jdoe";
        String title = "Task 99";

        Optional<Todo> todo = todoRepository.findByTitleAndUsername(title, username);

        assertThat(todo).isEmpty();
    }
}
