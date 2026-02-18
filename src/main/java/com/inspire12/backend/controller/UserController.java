package com.inspire12.backend.controller;

import com.inspire12.backend.dto.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

// TODO: @Tag 애노테이션으로 name="User", description="유저 API" 를 지정하세요
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

    // TODO: @Operation 애노테이션으로 summary="인사 메시지" 를 지정하세요
    @GetMapping("/hello")
    public String hello() {
        return "Hello, User!";
    }

    // TODO: @Operation 애노테이션으로 summary="유저 목록 조회" 를 지정하세요
    @GetMapping
    public List<User> getUsers() {
        return users;
    }

    // TODO: @Operation 애노테이션을 추가하세요
    @GetMapping("/{id}")
    public User getUser(
            /* TODO: @Parameter(description = "유저 ID") 를 추가하세요 */
            @PathVariable Long id) {
        return users.stream()
                .filter(u -> u.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    // TODO: @Operation 애노테이션을 추가하세요
    @GetMapping("/search")
    public List<User> searchUsers(
            /* TODO: @Parameter 를 추가하세요 */
            @RequestParam String name) {
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
    public ResponseEntity<User> getCurrentUser(
            @RequestHeader("Authorization") String authorization) {
        String token = authorization.replace("Bearer ", "");
        return ResponseEntity.ok(
                new User(1L, "홍길동 (token: " + token + ")", "hong@example.com")
        );
    }

    // TODO: @Operation 애노테이션을 추가하세요
    // TODO: @ApiResponses 로 200(조회 성공)과 400(잘못된 ID) 응답을 문서화하세요
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

    // TODO: @Operation 애노테이션을 추가하세요
    // TODO: @ApiResponse 로 201(생성 성공) 응답을 문서화하세요
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User request) {
        User newUser = new User(idGenerator.getAndIncrement(), request.name(), request.email());
        users.add(newUser);
        return ResponseEntity
                .created(URI.create("/users/" + newUser.id()))
                .body(newUser);
    }

    // ========== PUT ==========

    // TODO: @Operation 애노테이션을 추가하세요
    // TODO: @ApiResponses 로 200(수정 성공)과 404(유저를 찾을 수 없음) 응답을 문서화하세요
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

    // TODO: @Operation 애노테이션을 추가하세요
    // TODO: @ApiResponses 로 204(삭제 성공)과 404(유저를 찾을 수 없음) 응답을 문서화하세요
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean removed = users.removeIf(u -> u.id().equals(id));
        if (removed) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
