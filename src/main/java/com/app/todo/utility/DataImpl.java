package com.app.todo.utility;

import com.app.todo.entity.ToDo;
import com.app.todo.entity.enums.Priority;
import com.app.todo.repository.ToDoRepository;
import com.app.todo.service.ToDoService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataImpl {

    private final ToDoRepository toDoRepository;

    @PostConstruct
    private  void create(){
        init();
    }

    private void init() {
        toDoRepository.save(ToDo.builder()
                .title("Dentist Appointment")
                .description("I must go to dentist")
                .date("2023-03-22")
                .priority(Priority.HIGH)
                .build());
        toDoRepository.save(ToDo.builder()
                .title("Go to the Gym")
                .description("I should have chest workout")
                .date("2023-03-23")
                .priority(Priority.HIGH)
                .build());
        toDoRepository.save(ToDo.builder()
                .title("Birthday")
                .description("John's birthday, send a gift")
                .date("2023-03-27")
                .priority(Priority.MEDIUM)
                .build());


    }

}
