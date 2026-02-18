package com.inspire12.backend.controller;

import com.inspire12.backend.dto.User;
import com.inspire12.backend.service.UserService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
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
import java.util.List;
import java.util.Map;

// Controller: HTTP 요청/응답만 담당 (얇은 계층)
// 비즈니스 로직은 Service 에 위임
@Tag(name = "User", description = "유저 API")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final Counter userCreateCounter;

    public UserController(UserService userService, MeterRegistry meterRegistry) {
        this.userService = userService;
        this.userCreateCounter = Counter.builder("user.created.count")
                .description("유저 생성 횟수")
                .tag("controller", "UserController")
                .register(meterRegistry);
    }

    // ========== GET ==========

    @Operation(summary = "인사 메시지", description = "단순 문자열 응답을 반환합니다")
    @GetMapping("/hello")
    public String hello() {
        return "Hello, User!";
    }

    @Operation(summary = "유저 목록 조회", description = "전체 유저 목록을 반환합니다")
    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "유저 단건 조회", description = "ID로 유저를 조회합니다")
    @GetMapping("/{id}")
    public User getUser(@Parameter(description = "유저 ID") @PathVariable Long id) {
        return userService.getUserById(id);
    }

    @Operation(summary = "유저 검색", description = "이름으로 유저를 검색합니다")
    @GetMapping("/search")
    public List<User> searchUsers(@Parameter(description = "검색할 이름") @RequestParam String name) {
        return userService.searchByName(name);
    }

    @Operation(summary = "유저 목록 (페이징)", description = "페이지 단위로 유저를 조회합니다")
    @GetMapping("/page")
    public Map<String, Object> getUsersWithPage(
            @Parameter(description = "페이지 번호 (0부터 시작)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 크기") @RequestParam(defaultValue = "10") int size) {
        List<User> all = userService.getAllUsers();
        List<User> paged = all.stream()
                .skip((long) page * size)
                .limit(size)
                .toList();
        return Map.of(
                "page", page,
                "size", size,
                "totalElements", all.size(),
                "content", paged
        );
    }

    @Operation(summary = "현재 유저 조회", description = "Authorization 헤더의 토큰으로 현재 유저를 조회합니다")
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(
            @Parameter(description = "Bearer 토큰", example = "Bearer my-token")
            @RequestHeader("Authorization") String authorization) {
        String token = authorization.replace("Bearer ", "");
        return ResponseEntity.ok(
                new User(1L, "홍길동 (token: " + token + ")", "hong@example.com")
        );
    }

    @Operation(summary = "유저 상세 조회", description = "ID로 유저 상세 정보를 조회합니다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 ID")
    })
    @GetMapping("/{id}/detail")
    public User getUserDetail(@Parameter(description = "유저 ID") @PathVariable Long id) {
        return userService.getUserDetail(id);
    }

    // ========== POST ==========

    @Operation(summary = "유저 생성", description = "새로운 유저를 생성합니다")
    @ApiResponse(responseCode = "201", description = "생성 성공")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User request) {
        User created = userService.createUser(request);
        userCreateCounter.increment();
        return ResponseEntity
                .created(URI.create("/users/" + created.id()))
                .body(created);
    }

    // ========== PUT ==========

    @Operation(summary = "유저 수정", description = "기존 유저 정보를 수정합니다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "404", description = "유저를 찾을 수 없음")
    })
    @PutMapping("/{id}")
    public User updateUser(
            @Parameter(description = "유저 ID") @PathVariable Long id,
            @RequestBody User request) {
        return userService.updateUser(id, request);
    }

    // ========== DELETE ==========

    @Operation(summary = "유저 삭제", description = "유저를 삭제합니다")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "삭제 성공"),
            @ApiResponse(responseCode = "404", description = "유저를 찾을 수 없음")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@Parameter(description = "유저 ID") @PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
