package com.inspire12.backend.controller;

import com.inspire12.backend.dto.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    // 1. 가장 단순한 GET 요청 - 문자열 응답
    @GetMapping("/hello")
    public String hello() {
        return "Hello, User!";
    }

    // 2. 유저 목록 조회 - JSON 배열 응답
    @GetMapping
    public List<User> getUsers() {
        return List.of(
                new User(1L, "홍길동", "hong@example.com"),
                new User(2L, "김철수", "kim@example.com"),
                new User(3L, "이영희", "lee@example.com")
        );
    }

    // 3. PathVariable - 경로에서 값 추출
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return new User(id, "홍길동", "hong@example.com");
    }

    // 4. RequestParam - 쿼리 파라미터
    // GET /users/search?name=홍길동
    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam String name) {
        return List.of(
                new User(1L, name, "hong@example.com")
        );
    }

    // 5. RequestParam 기본값 + 페이징
    // GET /users/page?page=0&size=10
    @GetMapping("/page")
    public Map<String, Object> getUsersWithPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return Map.of(
                "page", page,
                "size", size,
                "totalElements", 100,
                "content", List.of(
                        new User(1L, "홍길동", "hong@example.com"),
                        new User(2L, "김철수", "kim@example.com")
                )
        );
    }

    // 6. RequestHeader - 요청 헤더 읽기
    // curl -H "Authorization: Bearer my-token" localhost:8080/users/me
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(@RequestHeader("Authorization") String authorization) {
        // 헤더에서 토큰 추출
        String token = authorization.replace("Bearer ", "");
        return ResponseEntity.ok(
                new User(1L, "홍길동 (token: " + token + ")", "hong@example.com")
        );
    }

    // 7. ResponseEntity - HTTP 상태코드 직접 제어
    @GetMapping("/{id}/detail")
    public ResponseEntity<User> getUserDetail(@PathVariable Long id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(
                new User(id, "홍길동", "hong@example.com")
        );
    }
}
