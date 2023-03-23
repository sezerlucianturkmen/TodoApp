package com.app.todo.controller;

import com.app.todo.dto.request.*;
import com.app.todo.dto.response.*;
import com.app.todo.service.ToDoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import  static com.app.todo.constant.Constant.*;

@RestController
@RequestMapping(TODO)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ToDoController {
    private final ToDoService toDoService;
    @PreAuthorize("hasAnyRole('ADMIN', 'COMMON')")
    @PostMapping(CREATE)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<ToDoResponseDto> createTodo(@RequestBody @Valid CreateToDoRequestDto dto) {
        return ResponseEntity.ok(toDoService.createToDo(dto));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'COMMON')")
    @PutMapping(DONE)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<ToDoResponseDto> doneToDo(@RequestBody @Valid DoneToDoRequestDto dto) {
        return ResponseEntity.ok(toDoService.doneToDo(dto));
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(FINDALL)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<ToDoResponseDto>> findAllToDos(){
        return ResponseEntity.ok(toDoService.findAllTodos());
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'COMMON')")
    @PostMapping(FINDALLINPROGRESS)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<ToDoResponseDto>> findAllToDosInProgress(@RequestBody @Valid TokenRequestDto dto){
        return ResponseEntity.ok(toDoService.findAllToDosInProgress(dto));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'COMMON')")
    @PostMapping(FINDALLDONE)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<ToDoResponseDto>> findAllToDosDone(@RequestBody @Valid TokenRequestDto dto){
        return ResponseEntity.ok(toDoService.findAllToDosDone(dto));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'COMMON')")
    @PutMapping(UPDATE)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<ToDoResponseDto> updateTodo(@RequestBody @Valid UpdateToDoRequestDto dto) {
        return ResponseEntity.ok(toDoService.updateToDo(dto));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'COMMON')")
    @PostMapping(FILTERBYKEYWORD)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<ToDoResponseDto>>  filterByKeyword(@RequestBody @Valid FilterToDoRequestDto dto) {
        return ResponseEntity.ok(toDoService.filterByKeyword(dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'COMMON')")
    @PostMapping(FILTERBYPRIORITY)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<ToDoResponseDto>>  filterByPriority(@RequestBody @Valid FilterByPriorityToDoRequestDto dto) {
        return ResponseEntity.ok(toDoService.filterByPriority(dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'COMMON')")
    @DeleteMapping(DELETE)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Boolean>  deleteToDo(@RequestBody @Valid DeleteToDoRequestDto dto) {
        return ResponseEntity.ok(toDoService.deleteToDo(dto));
    }
}
