package com.app.todo.repository.base;

import com.app.todo.entity.base.Base;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;


@NoRepositoryBean
public interface BaseRepository < T extends Base, Long> extends JpaRepository<T,Long> {
    /*
        Find Methods For Active Entities
     */



}
