package com.inspire12.backend.exception;

import com.inspire12.backend.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// TODO: @RestControllerAdvice 애노테이션을 추가하세요
// @RestControllerAdvice = @ControllerAdvice + @ResponseBody
// 모든 컨트롤러에서 발생하는 예외를 한 곳에서 처리
public class GlobalExceptionHandler {

    // 1. UserNotFoundException → 404
    // TODO: @ExceptionHandler 애노테이션으로 UserNotFoundException 을 처리하세요
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException e) {
        // TODO: ErrorResponse.of() 로 404 응답을 만들고 ResponseEntity 로 반환하세요
        return null;
    }

    // 2. InvalidRequestException → 400
    // TODO: @ExceptionHandler 애노테이션으로 InvalidRequestException 을 처리하세요
    public ResponseEntity<ErrorResponse> handleInvalidRequest(InvalidRequestException e) {
        // TODO: ErrorResponse.of() 로 400 응답을 만들고 ResponseEntity 로 반환하세요
        return null;
    }

    // 3. 그 외 모든 예외 → 500
    // TODO: @ExceptionHandler 애노테이션으로 Exception 을 처리하세요
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        // TODO: ErrorResponse.of() 로 500 응답을 만들고 ResponseEntity 로 반환하세요
        return null;
    }
}
