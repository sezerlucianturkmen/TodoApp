package com.app.todo.repository;

import com.app.todo.entity.User;
import com.app.todo.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends BaseRepository <User, Long> {

    @Query("SELECT u FROM User u WHERE u.state = 'ACTIVE' AND u.email = ?1")
    Optional<User> findByEmail(String email);

}
