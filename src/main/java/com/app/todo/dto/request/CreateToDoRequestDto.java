package com.app.todo.dto.request;

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
    private LocalDateTime date;
}
