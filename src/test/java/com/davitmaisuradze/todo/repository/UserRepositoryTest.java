package com.davitmaisuradze.todo.repository;

import com.davitmaisuradze.todo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@Sql(scripts = "/test-schema.sql")
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByUsername_WhenUsernameExists_ThenReturnOptionalOfUser() {
        String username = "bwayne";

        Optional<User> user = userRepository.findByUsername(username);

        assertThat(user).isPresent();
        assertThat(user.get().getUsername()).isEqualTo(username);
        assertThat(user.get().getFirstName()).isEqualTo("Bruce");
        assertThat(user.get().getLastName()).isEqualTo("Wayne");
    }

    @Test
    void testFindByUsername_WhenUsernameDoesNotExist_ThenReturnEmptyOptional() {
        String username = "NonExistingUsername";

        Optional<User> user = userRepository.findByUsername(username);

        assertThat(user).isEmpty();
    }
}
