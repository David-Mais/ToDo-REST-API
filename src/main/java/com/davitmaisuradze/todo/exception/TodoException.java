package com.davitmaisuradze.todo.exception;

import lombok.Getter;

@Getter
public class TodoException extends RuntimeException{
    private final String errorCode;

    public TodoException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
