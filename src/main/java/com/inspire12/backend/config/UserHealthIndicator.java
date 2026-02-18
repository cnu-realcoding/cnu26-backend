package com.inspire12.backend.config;

import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

// 커스텀 Health Indicator
// /actuator/health 에서 "user" 항목으로 표시됨
@Component
public class UserHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // 실제로는 DB 연결, 외부 API 상태 등을 체크
        boolean isHealthy = true;

        if (isHealthy) {
            return Health.up()
                    .withDetail("userService", "유저 서비스 정상")
                    .withDetail("userCount", 3)
                    .build();
        }
        return Health.down()
                .withDetail("userService", "유저 서비스 장애")
                .build();
    }
}
