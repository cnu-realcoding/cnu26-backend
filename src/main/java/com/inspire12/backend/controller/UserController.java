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

    @GetMapping("/hello")
    public String hello() {
        return "Hello, User!";
    }

    @GetMapping
    public List<User> getUsers() {
        return users;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return users.stream()
                .filter(u -> u.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam String name) {
        return users.stream()
                .filter(u -> u.name().contains(name))
                .toList();
    }

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

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(@RequestHeader("Authorization") String authorization) {
        String token = authorization.replace("Bearer ", "");
        return ResponseEntity.ok(
                new User(1L, "홍길동 (token: " + token + ")", "hong@example.com")
        );
    }

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
    // TODO: POST 매핑 애노테이션을 추가하세요
    public ResponseEntity<User> createUser(/* TODO: @RequestBody 애노테이션과 User 파라미터를 추가하세요 */) {
        // TODO: idGenerator 로 새 ID 를 생성하고, request 의 name, email 로 새 User 를 만드세요
        // TODO: users 리스트에 추가하세요
        // TODO: ResponseEntity.created(URI.create("/users/" + id)).body(newUser) 로 201 응답을 반환하세요
        return null;
    }

    // ========== PUT ==========

    // 9. PUT - 유저 정보 전체 수정
    // curl -X PUT -H "Content-Type: application/json" -d '{"name":"홍길동2","email":"hong2@example.com"}' localhost:8080/users/1
    // TODO: PUT 매핑 애노테이션을 추가하세요 (경로: /{id})
    public ResponseEntity<User> updateUser(/* TODO: @PathVariable Long id, @RequestBody User request */) {
        // TODO: users 리스트에서 id 가 일치하는 유저를 찾아 수정하세요
        // TODO: 찾으면 ResponseEntity.ok(updated) 반환
        // TODO: 못 찾으면 ResponseEntity.notFound().build() 반환
        return null;
    }

    // ========== DELETE ==========

    // 10. DELETE - 유저 삭제
    // curl -X DELETE localhost:8080/users/1
    // TODO: DELETE 매핑 애노테이션을 추가하세요 (경로: /{id})
    public ResponseEntity<Void> deleteUser(/* TODO: @PathVariable Long id */) {
        // TODO: users.removeIf() 로 해당 id 의 유저를 삭제하세요
        // TODO: 삭제 성공 시 ResponseEntity.noContent().build() 반환 (204)
        // TODO: 삭제 실패 시 ResponseEntity.notFound().build() 반환 (404)
        return null;
    }
}
