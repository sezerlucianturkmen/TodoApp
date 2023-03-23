package com.app.todo.dto.request;

import com.app.todo.entity.enums.Priority;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateToDoRequestDto {
    @NotBlank
    @NotNull
    private String title;
    private String description;
    private String date;
    private String priorityString;
    @NotBlank
    @NotNull
    private String Token;
}
