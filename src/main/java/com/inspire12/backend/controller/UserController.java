package com.inspire12.backend.controller;

import com.inspire12.backend.dto.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/users")
public class UserController {

    private final List<User> users = new ArrayList<>(List.of(
            new User(1L, "홍길동", "hong@example.com"),
            new User(2L, "김철수", "kim@example.com"),
            new User(3L, "이영희", "lee@example.com")
    ));
    private final AtomicLong idGenerator = new AtomicLong(4);

    // ========== GET ==========

    // 1. 가장 단순한 GET 요청 - 문자열 응답
    @GetMapping("/hello")
    public String hello() {
        return "Hello, User!";
    }

    // 2. 유저 목록 조회 - JSON 배열 응답
    @GetMapping
    public List<User> getUsers() {
        return users;
    }

    // 3. PathVariable - 경로에서 값 추출
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return users.stream()
                .filter(u -> u.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    // 4. RequestParam - 쿼리 파라미터
    // GET /users/search?name=홍길동
    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam String name) {
        return users.stream()
                .filter(u -> u.name().contains(name))
                .toList();
    }

    // 5. RequestParam 기본값 + 페이징
    // GET /users/page?page=0&size=10
    @GetMapping("/page")
    public Map<String, Object> getUsersWithPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<User> paged = users.stream()
                .skip((long) page * size)
                .limit(size)
                .toList();
        return Map.of(
                "page", page,
                "size", size,
                "totalElements", users.size(),
                "content", paged
        );
    }

    // 6. RequestHeader - 요청 헤더 읽기
    // curl -H "Authorization: Bearer my-token" localhost:8080/users/me
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(@RequestHeader("Authorization") String authorization) {
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

    // ========== POST ==========

    // 8. POST - RequestBody 로 JSON 을 받아 유저 생성
    // curl -X POST -H "Content-Type: application/json" -d '{"name":"박민수","email":"park@example.com"}' localhost:8080/users
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User request) {
        User newUser = new User(idGenerator.getAndIncrement(), request.name(), request.email());
        users.add(newUser);
        return ResponseEntity
                .created(URI.create("/users/" + newUser.id()))
                .body(newUser);
    }

    // ========== PUT ==========

    // 9. PUT - 유저 정보 전체 수정
    // curl -X PUT -H "Content-Type: application/json" -d '{"name":"홍길동2","email":"hong2@example.com"}' localhost:8080/users/1
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User request) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).id().equals(id)) {
                User updated = new User(id, request.name(), request.email());
                users.set(i, updated);
                return ResponseEntity.ok(updated);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // ========== DELETE ==========

    // 10. DELETE - 유저 삭제
    // curl -X DELETE localhost:8080/users/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean removed = users.removeIf(u -> u.id().equals(id));
        if (removed) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
