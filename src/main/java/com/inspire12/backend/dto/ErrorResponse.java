package com.inspire12.backend.dto;

import java.time.LocalDateTime;

// TODO: 에러 응답을 담는 record 를 정의하세요
// 필드: int status, String error, String message, LocalDateTime timestamp
public record ErrorResponse(/* TODO */) {

    // TODO: 정적 팩토리 메서드 of(int status, String error, String message) 를 만드세요
    // timestamp 는 LocalDateTime.now() 로 자동 생성
}
