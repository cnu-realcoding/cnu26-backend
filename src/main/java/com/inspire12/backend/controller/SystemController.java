package com.inspire12.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(name = "System", description = "시스템 정보 API")
@RestController
@RequestMapping("/system")
public class SystemController {

    private static final Logger log = LoggerFactory.getLogger(SystemController.class);

    private final Environment environment;

    // TODO: @Value 로 spring.application.name 값을 주입하세요
    // 힌트: @Value("${spring.application.name}")
    private String appName;

    // TODO: @Value 로 server.port 값을 주입하세요 (기본값 8080)
    // 힌트: @Value("${server.port:8080}")
    private String serverPort;

    public SystemController(Environment environment) {
        this.environment = environment;
    }

    @Operation(summary = "프로필 정보", description = "현재 활성 프로필과 설정 정보를 반환합니다")
    @GetMapping("/profile")
    public Map<String, Object> getProfile() {
        // TODO: environment.getActiveProfiles() 로 현재 활성 프로필을 가져오세요
        String profile = "unknown"; // TODO: 활성 프로필이 있으면 첫 번째 값, 없으면 "default"

        log.info("프로필 조회 - active: {}", profile);

        return Map.of(
                "appName", appName != null ? appName : "unknown",
                "activeProfile", profile,
                "serverPort", serverPort != null ? serverPort : "unknown"
        );
    }

    @Operation(summary = "로그 레벨 테스트", description = "각 로그 레벨로 메시지를 출력하여 현재 설정을 확인합니다")
    @GetMapping("/log-test")
    public Map<String, String> logTest() {
        // TODO: 5개 로그 레벨로 각각 메시지를 출력하세요
        // log.trace(), log.debug(), log.info(), log.warn(), log.error()

        return Map.of(
                "message", "콘솔에서 어떤 레벨까지 출력되는지 확인하세요"
        );
    }
}
