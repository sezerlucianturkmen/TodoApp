package com.app.todo.exception;
import lombok.Getter;

@Getter
public class ToDoManagerException extends RuntimeException{
    private final ErrorType errorType;

    public ToDoManagerException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public ToDoManagerException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}