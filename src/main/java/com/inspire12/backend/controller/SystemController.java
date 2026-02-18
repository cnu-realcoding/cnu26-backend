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

    // @Value 로 application.properties 값 주입
    @Value("${spring.application.name}")
    private String appName;

    @Value("${server.port:8080}")
    private String serverPort;

    public SystemController(Environment environment) {
        this.environment = environment;
    }

    // 현재 활성 프로필 확인
    @Operation(summary = "프로필 정보", description = "현재 활성 프로필과 설정 정보를 반환합니다")
    @GetMapping("/profile")
    public Map<String, Object> getProfile() {
        String[] activeProfiles = environment.getActiveProfiles();
        String profile = activeProfiles.length > 0 ? activeProfiles[0] : "default";

        log.info("프로필 조회 - active: {}", profile);

        return Map.of(
                "appName", appName,
                "activeProfile", profile,
                "serverPort", serverPort
        );
    }

    // 로그 레벨 데모 - 각 레벨별로 로그를 출력하여 현재 설정에서 어떤 레벨이 보이는지 확인
    @Operation(summary = "로그 레벨 테스트", description = "각 로그 레벨로 메시지를 출력하여 현재 설정을 확인합니다")
    @GetMapping("/log-test")
    public Map<String, String> logTest() {
        log.trace("TRACE 레벨 로그입니다");
        log.debug("DEBUG 레벨 로그입니다");
        log.info("INFO 레벨 로그입니다");
        log.warn("WARN 레벨 로그입니다");
        log.error("ERROR 레벨 로그입니다");

        String[] activeProfiles = environment.getActiveProfiles();
        String profile = activeProfiles.length > 0 ? activeProfiles[0] : "default";

        return Map.of(
                "message", "콘솔에서 어떤 레벨까지 출력되는지 확인하세요",
                "activeProfile", profile
        );
    }
}
