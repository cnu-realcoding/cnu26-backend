package com.inspire12.backend.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pages")
public class UserPageController {

    // 1. HTML 문자열 직접 반환
    // TODO: produces 를 사용하여 Content-Type 을 text/html 로 지정하세요
    @GetMapping(value = "/users" /* TODO */)
    public String usersPage() {
        // TODO: 유저 목록을 HTML <ul><li> 형태로 반환하세요
        return """
                <html>
                <body>
                    <h1>유저 목록</h1>
                </body>
                </html>
                """;
    }

    // 2. 같은 경로지만 JSON 으로 응답
    // TODO: produces 를 사용하여 Content-Type 을 application/json 으로 지정하세요
    @GetMapping(value = "/users" /* TODO */)
    public String usersJson() {
        // TODO: 유저 목록을 JSON 배열 문자열로 반환하세요
        return "[]";
    }

    // 3. PathVariable + HTML 응답
    // TODO: produces 를 text/html 로 지정하고, 경로에 {id} 를 포함하세요
    @GetMapping(value = "/users/{id}" /* TODO */)
    public String userDetailPage(@PathVariable Long id) {
        // TODO: id 를 포함한 유저 상세 HTML 을 반환하세요 (String.formatted 사용)
        return """
                <html>
                <body>
                    <h1>유저 상세</h1>
                </body>
                </html>
                """;
    }
}
