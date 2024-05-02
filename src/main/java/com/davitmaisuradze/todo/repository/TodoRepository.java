package com.davitmaisuradze.todo.repository;

import com.davitmaisuradze.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query("select t from Todo t where t.user.username = :username")
    List<Todo> findByUserUsername(@Param("username") String username);
    @Query("select t from Todo t where t.title = :title and t.user.username = :username")
    Optional<Todo> findByTitleAndUsername(@Param("title") String title, @Param("username") String username);
}
