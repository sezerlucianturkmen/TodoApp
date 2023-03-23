package com.app.todo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DeleteToDoRequestDto {
    @NotBlank
    @NotNull
    private String token;
    @NotNull
    private Long id;
}
