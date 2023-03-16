package com.app.todo.service;

import com.app.todo.dto.request.*;
import com.app.todo.dto.response.*;
import com.app.todo.entity.ToDo;
import com.app.todo.entity.enums.Priority;
import com.app.todo.entity.enums.Status;
import com.app.todo.exception.*;
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
        ToDo toDo = ToDoMapper.INSTANCE.toToDo(dto);
        return ToDoMapper.INSTANCE.toToDoResponseDto(toDoRepository.save(toDo));
    }

    public ToDoResponseDto doneTone(DoneToDoRequestDto dto) {
        ToDo toDo = toDoRepository.findById(dto.getId()).orElseThrow(()-> new ToDoManagerException(ErrorType.TODO_NOT_FOUND));
        toDo.setStatus(Status.DONE);
        return ToDoMapper.INSTANCE.toToDoResponseDto(toDoRepository.save(toDo));
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
                .map(ToDoMapper.INSTANCE::toToDoResponseDto)
                .collect(Collectors.toList());
    }
    public List<ToDoResponseDto> findAllToDosDone() {
        List<ToDo> toDoList = toDoRepository.findAllDone().orElseThrow(()-> new ToDoManagerException(ErrorType.TODO_NOT_FOUND));
        return toDoList.stream()
                .map(ToDoMapper.INSTANCE::toToDoResponseDto)
                .collect(Collectors.toList());
    }

    public ToDoResponseDto updateToDo(UpdateToDoRequestDto dto) {
        ToDo toDo = toDoRepository.findById(dto.getId()).orElseThrow(()-> new ToDoManagerException(ErrorType.TODO_NOT_FOUND));
        toDo.setTitle(dto.getTitle());
        toDo.setDescription(dto.getDescription());
        toDo.setDate(dto.getDate());
        toDo.setPriority(dto.getPriority());
        return ToDoMapper.INSTANCE.toToDoResponseDto(toDoRepository.save(toDo));
    }



}
