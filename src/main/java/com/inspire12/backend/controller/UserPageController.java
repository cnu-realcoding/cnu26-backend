package com.inspire12.backend.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController = @Controller + @ResponseBody
// 모든 메서드의 반환값이 HTTP 응답 본문(body)으로 직접 전송됨
// produces 로 Content-Type 을 지정할 수 있음
@RestController
@RequestMapping("/pages")
public class UserPageController {

    // 1. HTML 문자열 직접 반환
    // Content-Type: text/html
    @GetMapping(value = "/users", produces = MediaType.TEXT_HTML_VALUE)
    public String usersPage() {
        return """
                <html>
                <body>
                    <h1>유저 목록</h1>
                    <ul>
                        <li>홍길동 - hong@example.com</li>
                        <li>김철수 - kim@example.com</li>
                        <li>이영희 - lee@example.com</li>
                    </ul>
                </body>
                </html>
                """;
    }

    // 2. 같은 데이터를 JSON 으로 반환 (기본 동작)
    // Content-Type: application/json
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public String usersJson() {
        return """
                [
                    {"id": 1, "name": "홍길동", "email": "hong@example.com"},
                    {"id": 2, "name": "김철수", "email": "kim@example.com"},
                    {"id": 3, "name": "이영희", "email": "lee@example.com"}
                ]
                """;
    }

    // 3. PathVariable + HTML 응답
    // Content-Type: text/html
    @GetMapping(value = "/users/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String userDetailPage(@PathVariable Long id) {
        return """
                <html>
                <body>
                    <h1>유저 상세</h1>
                    <p>ID: %d</p>
                    <p>이름: 홍길동</p>
                    <p>이메일: hong@example.com</p>
                    <a href="/pages/users">목록으로</a>
                </body>
                </html>
                """.formatted(id);
    }
}
