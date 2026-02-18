package com.inspire12.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: @Tag 애노테이션으로 name="User Page", description="유저 HTML 페이지 API" 를 지정하세요
@RestController
@RequestMapping("/pages")
public class UserPageController {

    // TODO: @Operation 애노테이션으로 summary="유저 목록 HTML" 을 지정하세요
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

    // TODO: @Operation 애노테이션을 추가하세요
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

    // TODO: @Operation 애노테이션을 추가하세요
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
