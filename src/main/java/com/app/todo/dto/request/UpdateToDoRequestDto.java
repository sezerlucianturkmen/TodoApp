package com.app.todo.dto.request;

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
    @NotBlank
    @NotNull
    private Long id;
    private String description;
    private LocalDateTime date;
}
