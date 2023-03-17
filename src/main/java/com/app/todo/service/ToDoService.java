package com.app.todo.service;

import com.app.todo.config.jwt.JwtService;
import com.app.todo.dto.request.*;
import com.app.todo.dto.response.*;
import com.app.todo.entity.ToDo;
import com.app.todo.entity.User;
import com.app.todo.entity.enums.Status;
import com.app.todo.exception.*;
import com.app.todo.mapper.ToDoMapper;
import com.app.todo.repository.ToDoRepository;
import com.app.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoRepository toDoRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public ToDoResponseDto createToDo(CreateToDoRequestDto dto) {
        String email = jwtService.extractUsername(dto.getToken());
        User user = userRepository.findByEmail(email).get();
        ToDo toDo = ToDoMapper.INSTANCE.toToDo(dto);
        toDo.setUser(user);
        return ToDoMapper.INSTANCE.toToDoResponseDto(toDoRepository.save(toDo));
    }

    public ToDoResponseDto doneToDo(DoneToDoRequestDto dto) {
        String email = jwtService.extractUsername(dto.getToken());
        ToDo toDo = toDoRepository.findUserToDoById(dto.getId(),email).orElseThrow(()-> new ToDoManagerException(ErrorType.TODO_NOT_FOUND));
        toDo.setStatus(Status.DONE);
        return ToDoMapper.INSTANCE.toToDoResponseDto(toDoRepository.save(toDo));
    }

    public List<ToDoResponseDto> findAllTodos() {
        List<ToDo> toDoList = Optional.of(toDoRepository.findAll()).orElseThrow(()-> new ToDoManagerException(ErrorType.TODO_NOT_FOUND));
        return toDoList.stream()
                .map(ToDoMapper.INSTANCE::toToDoResponseDto)
                .collect(Collectors.toList());
    }

    public List<ToDoResponseDto> findAllToDosInProgress(TokenRequestDto dto) {
        String email = jwtService.extractUsername(dto.getToken());
        List<ToDo> toDoList = toDoRepository.findAllInProgress(email).orElseThrow(()-> new ToDoManagerException(ErrorType.TODO_NOT_FOUND));
        return toDoList.stream()
                .map(ToDoMapper.INSTANCE::toToDoResponseDto)
                .collect(Collectors.toList());
    }
    public List<ToDoResponseDto> findAllToDosDone(TokenRequestDto dto) {
        String email = jwtService.extractUsername(dto.getToken());
        List<ToDo> toDoList = toDoRepository.findAllDone(email).orElseThrow(()-> new ToDoManagerException(ErrorType.TODO_NOT_FOUND));
        return toDoList.stream()
                .map(ToDoMapper.INSTANCE::toToDoResponseDto)
                .collect(Collectors.toList());
    }

    public ToDoResponseDto updateToDo(UpdateToDoRequestDto dto) {
        String email = jwtService.extractUsername(dto.getToken());
        ToDo toDo = toDoRepository.findUserToDoById(dto.getId(),email).orElseThrow(()-> new ToDoManagerException(ErrorType.TODO_NOT_FOUND));
        ToDoMapper.INSTANCE.update(toDo,dto);
        return ToDoMapper.INSTANCE.toToDoResponseDto(toDoRepository.save(toDo));
    }

    public List<ToDoResponseDto> filterByKeyword(FilterToDoRequestDto dto) {
        String email = jwtService.extractUsername(dto.getToken());
        List<ToDo> toDoList = toDoRepository.findAllToDoByUser(email).orElseThrow(()-> new ToDoManagerException(ErrorType.TODO_NOT_FOUND));
        return Optional.of(toDoList.stream()
                .filter(toDo-> toDo.getTitle().toLowerCase().contains(dto.getKeyword().trim().toLowerCase())
                        || toDo.getDescription().toLowerCase().contains(dto.getKeyword().trim().toLowerCase()))
                .map(ToDoMapper.INSTANCE::toToDoResponseDto)
                .collect(Collectors.toList())).orElseThrow(()-> new ToDoManagerException(ErrorType.NO_RESULT));
    }

    public List<ToDoResponseDto> filterByPriority(FilterByPriorityToDoRequestDto dto) {
        String email = jwtService.extractUsername(dto.getToken());
        List<ToDo> toDoList = toDoRepository.findAllToDoByUser(email).orElseThrow(()-> new ToDoManagerException(ErrorType.TODO_NOT_FOUND));
        return Optional.of(toDoList.stream()
                .filter(toDo-> toDo.getPriority().toString().toUpperCase().contains(dto.getPriority().trim().toUpperCase()))
                .map(ToDoMapper.INSTANCE::toToDoResponseDto)
                .collect(Collectors.toList())).orElseThrow(()-> new ToDoManagerException(ErrorType.NO_RESULT));
    }
}
