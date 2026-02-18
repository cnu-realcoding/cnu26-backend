package com.inspire12.backend.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("유저를 찾을 수 없습니다. ID: " + id);
    }
}
