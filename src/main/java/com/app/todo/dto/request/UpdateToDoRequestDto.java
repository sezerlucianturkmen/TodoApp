package com.app.todo.dto.request;

import com.app.todo.entity.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateToDoRequestDto {

    private String title;
    @NotNull
    private Long id;
    private String description;
    private String date;
    private Priority priority;
    private String token;
}
