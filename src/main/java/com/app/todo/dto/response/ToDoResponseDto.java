package com.app.todo.dto.response;

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
    private LocalDateTime date;
}
