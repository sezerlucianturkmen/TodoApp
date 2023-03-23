package com.app.todo.dto.response;

import jakarta.persistence.Column;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthenticationResponseDto {
    private String token;
    @Builder.Default
    private Integer code = 400;
    private String firstName;
    private String lastName;
    private String email;
}
