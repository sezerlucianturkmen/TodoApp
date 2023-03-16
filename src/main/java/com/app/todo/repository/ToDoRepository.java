package com.app.todo.repository;

import com.app.todo.entity.ToDo;
import com.app.todo.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ToDoRepository extends BaseRepository <ToDo, Long> {

    @Query("SELECT t FROM ToDo t WHERE t.status = 'IN_PROGRESS' ")
    Optional<List<ToDo>> findAllInProgress();
    @Query("SELECT t FROM ToDo t WHERE t.status = 'DONE' ")
    Optional<List<ToDo>> findAllDone();

}
