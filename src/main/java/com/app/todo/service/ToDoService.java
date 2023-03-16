package com.app.todo.service;

import com.app.todo.dto.request.*;
import com.app.todo.dto.response.*;
import com.app.todo.entity.ToDo;
import com.app.todo.entity.enums.Status;
import com.app.todo.exception.ErrorType;
import com.app.todo.exception.ToDoManagerException;
import com.app.todo.mapper.ToDoMapper;
import com.app.todo.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoRepository toDoRepository;

    public ToDoResponseDto createToDo(CreateToDoRequestDto dto) {
        ToDo todo = ToDoMapper.INSTANCE.toToDo(dto);
        toDoRepository.save(todo);
        return ToDoMapper.INSTANCE.toToDoResponseDto(todo);
    }

    public ToDoResponseDto doneTone(DoneToDoRequestDto dto) {
        ToDo todo = toDoRepository.findById(dto.getId()).orElseThrow(()-> new ToDoManagerException(ErrorType.TODO_NOT_FOUND));
        todo.setStatus(Status.DONE);
        return ToDoMapper.INSTANCE.toToDoResponseDto(toDoRepository.save(todo));
    }

    public List<ToDoResponseDto> findAllTodos() {
        List<ToDo> toDoList = Optional.of(toDoRepository.findAll()).orElseThrow(()-> new ToDoManagerException(ErrorType.TODO_NOT_FOUND));
        return toDoList.stream()
                .map(ToDoMapper.INSTANCE::toToDoResponseDto)
                .collect(Collectors.toList());
    }

    public List<ToDoResponseDto> findAllToDosInProgress() {
        List<ToDo> toDoList = toDoRepository.findAllInProgress().orElseThrow(()-> new ToDoManagerException(ErrorType.TODO_NOT_FOUND));
        return toDoList.stream()
                .filter(todo-> todo.getStatus()==Status.IN_PROGRESS)
                .map(ToDoMapper.INSTANCE::toToDoResponseDto)
                .collect(Collectors.toList());
    }

    public ToDoResponseDto updateToDo(UpdateToDoRequestDto dto) {
        ToDo todo = toDoRepository.findById(dto.getId()).orElseThrow(()-> new ToDoManagerException(ErrorType.TODO_NOT_FOUND));
        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        todo.setDate(dto.getDate());
        return ToDoMapper.INSTANCE.toToDoResponseDto(toDoRepository.save(todo));


    }
}
