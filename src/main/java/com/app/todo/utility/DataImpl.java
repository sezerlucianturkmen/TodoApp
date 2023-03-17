package com.app.todo.utility;

import com.app.todo.entity.ToDo;
import com.app.todo.entity.User;
import com.app.todo.entity.enums.Priority;
import com.app.todo.entity.enums.Role;
import com.app.todo.repository.ToDoRepository;
import com.app.todo.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataImpl {

    private final ToDoRepository toDoRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    private  void create(){
        init();
    }

    private void init() {

        userRepository.save(User.builder()
                        .email("admin@gmail.com")
                        .role(Role.ADMIN)
                        .firstName("admin")
                        .lastName("admin")
                        .password(passwordEncoder.encode("12341234"))
                .build());
        User sampleUser1 = userRepository.save(User.builder()
                .email("sezer@gmail.com")
                .role(Role.COMMON)
                .firstName("sezer")
                .lastName("turkmen")
                .password(passwordEncoder.encode("12341234"))
                .build());
        User sampleUser2 = userRepository.save(User.builder()
                .email("lucian@gmail.com")
                .role(Role.COMMON)
                .firstName("lucian")
                .lastName("turkmen")
                .password(passwordEncoder.encode("12341234"))
                .build());
        toDoRepository.save(ToDo.builder()
                .title("Dentist Appointment")
                .description("I must go to dentist")
                .date("2023-03-22")
                .priority(Priority.HIGH)
                .user(sampleUser1)
                .build());
        toDoRepository.save(ToDo.builder()
                .title("Go to the Gym")
                .description("I should have chest workout")
                .date("2023-03-23")
                .priority(Priority.NONE)
                .user(sampleUser1)
                .build());
        toDoRepository.save(ToDo.builder()
                .title("Birthday")
                .description("John's birthday, send a gift")
                .date("2023-03-27")
                .priority(Priority.MEDIUM)
                .user(sampleUser2)
                .build());


    }

}
