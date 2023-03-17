package com.app.todo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FilterByPriorityToDoRequestDto {
    @NotBlank
    @NotNull
    private String priority;
    @NotBlank
    @NotNull
    private String token;

}
