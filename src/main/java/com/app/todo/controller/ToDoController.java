package com.app.todo.controller;

import com.app.todo.dto.request.CreateToDoRequestDto;
import com.app.todo.dto.request.DoneToDoRequestDto;
import com.app.todo.dto.request.FilterToDoRequestDto;
import com.app.todo.dto.request.UpdateToDoRequestDto;
import com.app.todo.dto.response.ToDoResponseDto;
import com.app.todo.service.ToDoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import  static com.app.todo.constant.Constant.*;

@RestController
@RequestMapping(TODO)
@RequiredArgsConstructor
public class ToDoController {
    private final ToDoService toDoService;

    @PostMapping(CREATE)
    public ResponseEntity<ToDoResponseDto> createTodo(@RequestBody @Valid CreateToDoRequestDto dto) {
        return ResponseEntity.ok(toDoService.createToDo(dto));
    }
    @PostMapping(DONE)
    public ResponseEntity<ToDoResponseDto> doneToDo(@RequestBody @Valid DoneToDoRequestDto dto) {
        return ResponseEntity.ok(toDoService.doneToDo(dto));
    }
    @GetMapping(FINDALL)
    public ResponseEntity<List<ToDoResponseDto>> findAllToDos(){
        return ResponseEntity.ok(toDoService.findAllTodos());
    }
    @GetMapping(FINDALLINPROGRESS)
    public ResponseEntity<List<ToDoResponseDto>> findAllToDosInProgress(){
        return ResponseEntity.ok(toDoService.findAllToDosInProgress());
    }
    @GetMapping(FINDALLDONE)
    public ResponseEntity<List<ToDoResponseDto>> findAllToDosDone(){
        return ResponseEntity.ok(toDoService.findAllToDosDone());
    }
    @PutMapping(UPDATE)
    public ResponseEntity<ToDoResponseDto> updateTodo(@RequestBody @Valid UpdateToDoRequestDto dto) {
        return ResponseEntity.ok(toDoService.updateToDo(dto));
    }
    @PostMapping(FILTERBYKEYWORD)
    public ResponseEntity<List<ToDoResponseDto>>  filterByKeyword(@RequestBody @Valid FilterToDoRequestDto dto) {
        return ResponseEntity.ok(toDoService.filterByKeyword(dto));
    }
}
