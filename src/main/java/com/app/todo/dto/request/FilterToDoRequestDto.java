package com.app.todo.dto.request;

import com.app.todo.entity.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FilterToDoRequestDto {
    @NotBlank
    @NotNull
    private String keyword;
    private String token;

}
