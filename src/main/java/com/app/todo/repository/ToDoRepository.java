package com.app.todo.repository;

import com.app.todo.entity.ToDo;
import com.app.todo.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

import static org.hibernate.sql.ast.Clause.FROM;

@Repository
public interface ToDoRepository extends BaseRepository <ToDo, Long> {

    @Query("SELECT t FROM ToDo t WHERE t.status = 'IN_PROGRESS' AND t.user = (SELECT u FROM User u WHERE u.email = ?1)")
    Optional<List<ToDo>> findAllInProgress(String email);
    @Query("SELECT t FROM ToDo t WHERE t.status = 'DONE' AND t.user = (SELECT u FROM User u WHERE u.email = ?1) ")
    Optional<List<ToDo>> findAllDone(String email);
    @Query("SELECT t FROM ToDo t WHERE t.id = ?1 AND t.user = (SELECT u FROM User u WHERE u.email = ?2) ")
    Optional<ToDo> findUserToDoById(Long id, String email);
    @Query("SELECT t FROM ToDo t WHERE t.user = (SELECT u FROM User u WHERE u.email = ?1) ")
    Optional<List<ToDo>> findAllToDoByUser(String email);
}
