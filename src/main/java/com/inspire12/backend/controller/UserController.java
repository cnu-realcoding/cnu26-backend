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

// TODO: REST 컨트롤러 애노테이션을 추가하세요
// TODO: "/users" 경로로 매핑하세요
public class UserController {

    // 1. 가장 단순한 GET 요청 - 문자열 응답
    // TODO: GET 매핑 애노테이션을 추가하세요 (경로: /hello)
    public String hello() {
        return "Hello, User!";
    }

    // 2. 유저 목록 조회 - JSON 배열 응답
    // TODO: GET 매핑 애노테이션을 추가하세요
    public List<User> getUsers() {
        return List.of(
                // TODO: User 객체 3개를 생성하세요
        );
    }

    // 3. PathVariable - 경로에서 값 추출
    // TODO: GET 매핑 애노테이션을 추가하세요 (경로에 {id} 포함)
    public User getUser(/* TODO: PathVariable 애노테이션과 파라미터를 추가하세요 */) {
        return null; // TODO: id를 사용하여 User 객체를 반환하세요
    }

    // 4. RequestParam - 쿼리 파라미터
    // GET /users/search?name=홍길동
    // TODO: GET 매핑 애노테이션을 추가하세요 (경로: /search)
    public List<User> searchUsers(/* TODO: RequestParam 애노테이션과 파라미터를 추가하세요 */) {
        return List.of(
                // TODO: 전달받은 name을 사용하여 User 객체를 반환하세요
        );
    }

    // 5. RequestParam 기본값 + 페이징
    // GET /users/page?page=0&size=10
    // TODO: GET 매핑 애노테이션을 추가하세요 (경로: /page)
    public Map<String, Object> getUsersWithPage(
            /* TODO: RequestParam(defaultValue = "0") int page, RequestParam(defaultValue = "10") int size */) {
        return Map.of(
                // TODO: page, size, totalElements, content 를 포함하는 Map 을 반환하세요
        );
    }

    // 6. RequestHeader - 요청 헤더 읽기
    // curl -H "Authorization: Bearer my-token" localhost:8080/users/me
    // TODO: GET 매핑 애노테이션을 추가하세요 (경로: /me)
    public ResponseEntity<User> getCurrentUser(/* TODO: RequestHeader 애노테이션으로 "Authorization" 헤더를 받으세요 */) {
        // TODO: 헤더에서 "Bearer " 이후의 토큰을 추출하고 User 를 ResponseEntity.ok() 로 반환하세요
        return null;
    }

    // 7. ResponseEntity - HTTP 상태코드 직접 제어
    // TODO: GET 매핑 애노테이션을 추가하세요 (경로: /{id}/detail)
    public ResponseEntity<User> getUserDetail(/* TODO: PathVariable 파라미터 */) {
        // TODO: id <= 0 이면 ResponseEntity.badRequest().build() 반환
        // TODO: 정상이면 ResponseEntity.ok() 로 User 반환
        return null;
    }
}
