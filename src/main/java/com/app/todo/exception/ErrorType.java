package com.app.todo.exception;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    INTERNAL_ERROR(2000, "Internal Server Error", INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(2001, "Invalid Parameter Error", BAD_REQUEST),
    INVALID_TOKEN(2002, "Invalid Token", BAD_REQUEST),

    TODO_NOT_FOUND(1001, "ToDo is not found", INTERNAL_SERVER_ERROR),
    TODO_NOT_CREATED(1002, "ToDo detail is not created", INTERNAL_SERVER_ERROR),
    NO_RESULT(1003, "There is no any result to be shown", INTERNAL_SERVER_ERROR),
    ALREADY_EXIST(1004, "There is already exist", INTERNAL_SERVER_ERROR);



    private int code;
    private String message;
    HttpStatus httpStatus;

}