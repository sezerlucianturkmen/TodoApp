package com.app.todo.controller;

import com.app.todo.dto.request.LoginRequestDto;
import com.app.todo.dto.request.RegisterRequestDto;
import com.app.todo.dto.response.AuthenticationResponseDto;
import com.app.todo.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<AuthenticationResponseDto> register(@RequestBody @Valid RegisterRequestDto dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping("/authenticate")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody @Valid LoginRequestDto dto) {
        return ResponseEntity.ok(authService.authenticate(dto));
    }
}
