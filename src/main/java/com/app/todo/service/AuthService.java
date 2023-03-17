package com.app.todo.service;


import com.app.todo.config.jwt.JwtService;
import com.app.todo.dto.request.LoginRequestDto;
import com.app.todo.dto.request.RegisterRequestDto;
import com.app.todo.dto.response.AuthenticationResponseDto;
import com.app.todo.entity.User;
import com.app.todo.exception.ErrorType;
import com.app.todo.exception.ToDoManagerException;
import com.app.todo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponseDto register(RegisterRequestDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new ToDoManagerException(ErrorType.ALREADY_EXIST);
        }
        User auth = userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .role(dto.getRole())
                .build());
        return AuthenticationResponseDto.builder()
                .token(jwtService.generateToken(auth))
                .build();
    }

    public AuthenticationResponseDto authenticate(LoginRequestDto dto) {
        Optional<User> user = userRepository.findByEmail(dto.getEmail());
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Username does not exist.");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );
        return AuthenticationResponseDto.builder()
                .token(jwtService.generateToken(user.get()))
                .build();
    }
}
