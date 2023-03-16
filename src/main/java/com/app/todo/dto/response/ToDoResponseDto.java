package com.app.todo.dto.response;

import com.app.todo.entity.enums.Priority;
import com.app.todo.entity.enums.Status;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ToDoResponseDto {
    private Long id;
    private String title;
    private String description;
    private String date;
    private Status status;
    private Priority priority;
}
